package com.hanson.tank.dto;

import com.hanson.Player;
import com.hanson.enums.Direction;
import com.hanson.tank.aggregate.IPlayer;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.entity.BattleMap;
import com.hanson.tank.entity.Boom;
import com.hanson.tank.entity.Bullet;
import com.hanson.tank.entity.Wall;
import com.hanson.tank.loader.JarLoader;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GameData {


    /**
     * 内部玩家列表
     */
    private List<IPlayer> iPlayers;

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * 外部玩家列表
     */
    private List<Player> players;
    /**
     * 地图
     */
    private BattleMap battleMap;

    /**
     * 游戏状态
     */
    private boolean start;

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }


    public List<IPlayer> getIPlayers() {
        return iPlayers;
    }

    private List<Bullet> bullets;

    private List<Boom> booms;

    public List<Wall> getWalls() {
        return walls;
    }

    private List<Wall> walls;


    private int[][] map;

    public void refreshMap(){

        map = new int[GameConstants.GRID_WIDTH][GameConstants.GRID_WIDTH];
        getWalls().forEach(wall -> {
            if(wall.isActive()){
                map[wall.getX()][wall.getY()] = wall.getStuffType().getType();

            }
        });

        getBullets().forEach(bullet -> {
            if(bullet.isActive()){
                map[bullet.getX()][bullet.getY()] = bullet.getStuffType().getType();

            }
        });

        iPlayers.forEach(player->{
            player.getTanks().forEach((id, iTank) ->{
                if(iTank.isActive()){
                    map[iTank.getX()][iTank.getY()] = iTank.getStuffType().getType();
                }
            });
        });
    }


    public void generatePlayers(int num) {

        Direction genDirection[] = new Direction[]{Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};

        iPlayers = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            IPlayer player = new IPlayer(i, "default");
            Direction dir = genDirection[i];
            Color color = GameConstants.TANK_COLOR[dir.getDir()];
            player.generateTanks(dir, GameConstants.INIT_TANK_NUM, color);
            iPlayers.add(player);
        }

        bullets = new ArrayList<>();
        booms = new ArrayList<>();
    }

    public void generateWalls(){
        walls = new ArrayList<>();
        int middle = GameConstants.GAME_PANEL_GRID_COUNT / 2;
        int four = GameConstants.GAME_PANEL_GRID_COUNT / 4;
        for (int i = middle - 5; i <= middle + 5; i++) {
            walls.add(new Wall(four,i));
            walls.add(new Wall(four * 3, i));
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Boom> getBooms() {
        return booms;
    }

    public void clearBullets() {
        bullets = bullets.stream().filter(bullet -> bullet.isActive()).collect(Collectors.toList());
    }

    public void clearBoom() {
        booms = booms.stream().filter(boom -> boom.isActive()).collect(Collectors.toList());
    }

    public void loadPlayer(File[] playerFiles) {
        int playerNum = playerFiles.length;
        generatePlayers(playerNum);

        players = new ArrayList<>();
        for (int i = 0; i < playerNum; i++) {
            try {
                JarLoader loader = new JarLoader(playerFiles[i].toURL());
                Player player = loader.loadJarAndGetPlayer();
                players.add(player);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
