package com.hanson.tank.demo;

import com.hanson.Player;
import com.hanson.entity.Command;
import com.hanson.entity.Enemy;
import com.hanson.entity.Tank;
import com.hanson.enums.Action;
import com.hanson.enums.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Play implements Player {

    private static String[] NAME_REPO = {"随机战斗","斗战圣佛","宇宙猴子","美国队长","蝙蝠侠"};

    /**
     * 枚举方向为 上，右，下，左 依次转动
     */
    private int nameId;

    Random random = new Random();

    private String name;

    private boolean searchFlag;

    @Override
    public List<Command> getAction(int[][] map, List<Tank> my, List<Enemy> enemies) {
        switch (nameId){
            case 1:
            case 2:
            case 3:
            case 4:
            {
                return normalBattle(map,my,enemies);
            }
            default:{
                return randomBattle(map,my);
            }
        }


    }


    /**
     * 普通策略算法
     * @param map
     * @param my
     * @return
     */
    public List<Command> normalBattle(int[][] map, List<Tank> my,List<Enemy> enemies){

        List<Command> commands = new ArrayList<>();
        for (Tank tank : my) {
            SearchNode result = null;
            for (Enemy enemy : enemies) {
                for (Tank enemyTank : enemy.getTanks()) {
                    searchFlag = false;
                    SearchNode node = searchDirPath(map,tank.getX(),tank.getY(),enemyTank.getX(),enemyTank.getY(),tank.getDirection(),0);
                    if(result == null){
                        result = node;
                    }else if(node != null && result.cost > node.cost){
                        result = node;
                    }
                }
            }
            if (result != null) {
                Command command = new Command(tank.getId());
                if(result.cost == 0){
                    command.setAction(Action.Fire);
                }else if(tank.getDirection() == result.dir){
                    command.setAction(Action.Move);
                }else if(tank.getDirection() != result.dir){
                    command.setAction(Action.Turn);
                    command.setDirection(result.dir);
                }
                commands.add(command);
            }
        }

        return commands;
    }


    /**
     * 随机战斗算法
     * @param map
     * @param my
     * @return
     */
    public List<Command> randomBattle(int[][] map, List<Tank> my){
        List<Command> commands = new ArrayList<>();

        my.forEach( tank -> {
            Command command = new Command(tank.getId());
            command.setAction(Action.fromAction(random.nextInt(4)));
            commands.add(command);

            if(command.getAction() == Action.Turn){
                command.setDirection(Direction.getDirection(random.nextInt(4)));
            }
        });

        return commands;
    }


    /**
     * 根据方向的最优寻路算法，只要在一条直线上即可
     * @param map  当前地图
     * @param startX 起始X坐标
     * @param startY 起始Y坐标
     * @param targetX 目标X坐标
     * @param targetY 目标Y坐标
     */
    public SearchNode searchDirPath(int[][]map,int startX,int startY,int targetX,int targetY,Direction nowDir,int cost){

        if(searchFlag == true){
            return null;
        }
        if (startX == targetX || startY == targetY) {
            Direction relativeDir = null;
            if (startY - targetY < 0) {
                relativeDir = Direction.DOWN;
            } else if (startY - targetY > 0) {
                relativeDir = Direction.UP;
            } else if (startX - targetX < 0) {
                relativeDir = Direction.RIGHT;
            } else if (startX - targetX > 0) {
                relativeDir = Direction.LEFT;
            }
            if (relativeDir != nowDir) {
                cost++;
            }
            searchFlag = true;
            return new SearchNode(relativeDir, cost);
        }


        SearchNode optNode = null;

        for (int i = 0; i < Direction.DIR_STEP.length; i++) {
            int nextX = startX + Direction.DIR_STEP[i][0];
            int nextY = startY + Direction.DIR_STEP[i][1];

            if(!checkReach(map,nextX,nextY)){
                continue;
            }
            map[nextX][nextY] = -1;

            SearchNode tmpNode;

            int nextCost = cost + 1;
            if(nowDir.getDir() != i){
                nextCost += 1;
            }
            tmpNode = searchDirPath(map,nextX,nextY,targetX,targetY,Direction.getDirection(i),nextCost);

            if(optNode == null){
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
        if(name == null){
            nameId = random.nextInt(NAME_REPO.length);
            name = NAME_REPO[nameId];
        }
        return name;
    }

    private boolean checkReach(int[][] map,int x,int y){
        if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) {
            return false;
        }
        if(map[x][y] != 0){
            return  false;
        }
        return true;
    }

    class SearchNode {

        public SearchNode(Direction dir,int cost){
            this.dir = dir;
            this.cost = cost;
        }


        public Direction dir;
        public int cost;
    }
}
