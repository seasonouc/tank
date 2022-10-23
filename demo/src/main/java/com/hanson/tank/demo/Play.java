package com.hanson.tank.demo;

import com.hanson.Player;
import com.hanson.entity.Camp;
import com.hanson.entity.Command;
import com.hanson.entity.Home;
import com.hanson.entity.Tank;
import com.hanson.enums.Action;
import com.hanson.enums.Direction;
import com.hanson.enums.StuffType;

import java.util.*;

public class Play implements Player {

    private static String[] NAME_REPO = {"随机战斗", "斗战圣佛", "宇宙猴子", "美国队长", "蝙蝠侠"};

    /**
     * 枚举方向为 上，右，下，左 依次转动
     */
    private int nameId;

    Random random = new Random();

    private String name;

    private boolean searchFlag;

    @Override
    public List<Command> getAction(int[][] map, Camp my, List<Camp> enemies) {
        switch (nameId) {
            case 1:
            case 2: {
                return SPFABattle(map,my,enemies);            }
            default: {
                return randomBattle(map, my);
            }
        }


    }


    /**
     * 普通策略算法,暴力打法
     *
     * @param map
     * @param my
     * @return
     */
    public List<Command> normalBattle(int[][] map, Camp my, List<Camp> enemies) {

        List<Command> commands = new ArrayList<>();
        for (Tank tank : my.getTanks()) {
            SearchNode result = null;
            for (Camp camp : enemies) {

                searchFlag = false;
                SearchNode node = searchDirPath(map, tank.getX(), tank.getY(), camp.getHome().getX(), camp.getHome().getY(), tank.getDirection(), 0);
                if (result == null) {
                    result = node;
                } else if (node != null && result.cost > node.cost) {
                    result = node;
                }

            }
            if (result != null) {
                Command command = new Command(tank.getId());
                if (result.cost == 0) {
                    command.setAction(Action.Fire);
                } else if (tank.getDirection() == result.dir) {
                    command.setAction(Action.Move);
                } else if (tank.getDirection() != result.dir) {
                    command.setAction(Action.Turn);
                    command.setDirection(result.dir);
                }
                commands.add(command);
            }
        }

        return commands;
    }

    /**
     * 广度优先搜索打法
     *
     * @param map
     * @param my
     * @return
     */
    public List<Command> SPFABattle(int[][] map, Camp my, List<Camp> enemies) {
        List<Command> commands = new ArrayList<>();
        for (Tank tank : my.getTanks()) {
            boolean find = false;
            for (Camp enemy : enemies) {
                for (Tank enemyTank : enemy.getTanks()) {
                    Direction relativeDir = relativeDir(tank.getX(), tank.getY(), enemyTank.getX(), enemyTank.getY());
                    if (relativeDir != null && !getBlockInLine(map, tank.getX(), tank.getY(), enemyTank.getX(), enemyTank.getY())) {
                        Command command = new Command(tank.getId());
                        if (tank.getDirection() == relativeDir) {
                            command.setAction(Action.Fire);
                        } else {
                            command.setAction(Action.Turn);
                            command.setDirection(relativeDir);
                        }
                        commands.add(command);
                        find = true;
                        break;
                    }

                }
                if (find) {
                    break;
                }
                Home home = enemy.getHome();
                Direction relativeEnemyHome = relativeDir(tank.getX(), tank.getY(), home.getX(), home.getY());
                if (relativeEnemyHome != null && !getBlockInLine(map, tank.getX(), tank.getY(), home.getX(), home.getY())) {
                    Command command = new Command(tank.getId());
                    if (tank.getDirection() == relativeEnemyHome) {
                        command.setAction(Action.Fire);
                    } else {
                        command.setAction(Action.Turn);
                        command.setDirection(relativeEnemyHome);
                    }
                    commands.add(command);
                    find = true;
                }
                if (find) {
                    break;
                }
                SearchNode node = searchTarget(map, tank.getX(), tank.getY(), home.getX(), home.getY(), tank.getDirection());
                rollbackMap(map);
                if (node != null) {
                    Command command = new Command(tank.getId());
                    if(node.dir == tank.getDirection()){
                        command.setAction(Action.Move);
                    }else{
                        command.setAction(Action.Turn);
                        command.setDirection(node.dir);
                    }
                    commands.add(command);
                }
            }
        }
        return commands;
    }

    /**
     * 还原被 修改的map
     *
     * @param map
     */
    private void rollbackMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == -1) {
                    map[i][j] = 0;
                }
            }
        }
    }

    /**
     * 判断两个位置是否位于一条直线上
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    private Direction relativeDir(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            if (y1 < y2) {
                return Direction.DOWN;
            } else if (y1 > y2) {
                return Direction.UP;
            }
        } else if (y1 == y2) {
            if (x1 > x2) {
                return Direction.LEFT;
            } else if (x1 < x2) {
                return Direction.RIGHT;
            }
        }
        return null;
    }

    private boolean getBlockInLine(int[][] map, int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            int start = Math.min(y1, y2);
            int end = Math.max(y1, y2);

            for (int i = start + 1; i < end; i++) {
                StuffType curBlockType = StuffType.fromType(map[i][x1]);
                if (curBlockType == StuffType.Iron || curBlockType == StuffType.Wall) {
                    return true;
                }
            }
        } else {
            int start = Math.min(x1, x2);
            int end = Math.max(x1, x2);
            for (int i = start + 1; i < end; i++) {
                StuffType curBlockType = StuffType.fromType(map[y1][i]);
                if (curBlockType == StuffType.Iron || curBlockType == StuffType.Wall) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 随机战斗算法
     *
     * @param map
     * @param my
     * @return
     */
    public List<Command> randomBattle(int[][] map, Camp my) {
        List<Command> commands = new ArrayList<>();



        my.getTanks().forEach(tank -> {
            Command command = new Command(tank.getId());
            command.setAction(Action.fromAction(random.nextInt(4)));
            commands.add(command);

            if (command.getAction() == Action.Turn) {
                command.setDirection(Direction.getDirection(random.nextInt(4)));
            }else if(command.getAction() == Action.Fire){
                Direction direction = relativeDir(tank.getX(),tank.getY(),my.getHome().getX(),my.getHome().getY());
                if(tank.getDirection() == direction){
                    command.setAction(Action.Move);
                }
                for (Tank myTank : my.getTanks()) {
                    direction = relativeDir(tank.getX(),tank.getY(),myTank.getX(),myTank.getY());
                    if(tank.getDirection() == direction){
                        command.setAction(Action.Move);
                    }
                }
            }
        });

        return commands;
    }

    /**
     * 根据目标来寻路
     *
     * @param map
     * @param startX
     * @param startY
     * @param targetX
     * @param targetY
     * @param nowDir
     * @return
     */
    public SearchNode searchTarget(int[][] map, int startX, int startY, int targetX, int targetY, Direction nowDir) {
        Queue<SearchNode> queue = new ArrayDeque<>();

        SearchNode startNode = new SearchNode(startX, startY, nowDir, 0);
        queue.add(startNode);
        SearchNode endNode = null;

        while (!queue.isEmpty()) {
            SearchNode node = queue.poll();

            for (int i = 0; i < Direction.DIR_STEP.length; i++) {
                int nextX = node.x + Direction.DIR_STEP[i][0];
                int nextY = node.y + Direction.DIR_STEP[i][1];

                if (nextX == targetX && nextY == targetY) {
                    endNode =  node;
                    break;
                }

                if (!checkReach(map, nextX, nextY)) {
                    continue;
                }
                map[nextY][nextX] = -1;
                SearchNode tmpNode = new SearchNode(nextX, nextY, Direction.getDirection(i), node.cost + 1);
                tmpNode.preNode = node;

                if (node.dir.getDir() != i) {
                    tmpNode.cost++;
                }
                queue.add(tmpNode);
            }
        }

        while (endNode != null && endNode.preNode != null) {
            if(endNode.preNode.preNode == null){
                break;
            }
            endNode = endNode.preNode;
        }
        return endNode;
    }

    /**
     * 根据方向的最优寻路算法，只要在一条直线上即可，暴力寻路算法
     *
     * @param map     当前地图
     * @param startX  起始X坐标
     * @param startY  起始Y坐标
     * @param targetX 目标X坐标
     * @param targetY 目标Y坐标
     */
    public SearchNode searchDirPath(int[][] map, int startX, int startY, int targetX, int targetY, Direction nowDir, int cost) {

        if (searchFlag == true) {
            return null;
        }
        Direction relativePos = relativeDir(startX,startY,targetX,targetY);

        if(relativePos != null && !getBlockInLine(map,startX,startY,targetX,targetY)){
            searchFlag = true;
            return new SearchNode(relativePos,cost);
        }


        SearchNode optNode = null;

        for (int i = 0; i < Direction.DIR_STEP.length; i++) {
            int nextX = startX + Direction.DIR_STEP[i][0];
            int nextY = startY + Direction.DIR_STEP[i][1];

            if (!checkReach(map, nextX, nextY)) {
                continue;
            }
            map[nextX][nextY] = -1;

            SearchNode tmpNode;

            int nextCost = cost + 1;
            if (nowDir.getDir() != i) {
                nextCost += 1;
            }
            tmpNode = searchDirPath(map, nextX, nextY, targetX, targetY, Direction.getDirection(i), nextCost);

            if (optNode == null) {
                optNode = tmpNode;
            } else if (tmpNode != null && optNode.cost > tmpNode.cost) {
                optNode = tmpNode;
            }

            map[nextX][nextY] = 0;
        }

        return optNode;
    }

    @Override
    public String getName() {
        if (name == null) {
            nameId = random.nextInt(NAME_REPO.length);
            name = NAME_REPO[nameId];
        }
        return name;
    }

    private boolean checkReach(int[][] map, int x, int y) {
        if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) {
            return false;
        }
        if (map[y][x] != 0) {
            return false;
        }
        return true;
    }

    class SearchNode {

        public SearchNode(int x, int y, Direction dir, int cost) {
            this.dir = dir;
            this.cost = cost;
            this.x = x;
            this.y = y;
        }

        public SearchNode(Direction dir, int cost) {
            this.dir = dir;
            this.cost = cost;
        }


        public int x;
        public int y;
        public Direction dir;
        public int cost;
        public SearchNode preNode;
    }
}
