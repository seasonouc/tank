package com.hanson.tank.aggregate;

import com.hanson.Player;
import com.hanson.entity.Command;
import com.hanson.entity.Enemy;
import com.hanson.entity.Tank;
import com.hanson.enums.Direction;
import com.hanson.enums.StuffType;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.entity.Bullet;
import com.hanson.tank.entity.ITank;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IPlayer {

    public int getId() {
        return id;
    }

    /**
     * 玩家  id
     */
    private int id;

    /**
     * 玩家名称
     */
    private String name;



    /**
     * 存活坦克数量
     */
    private int aliveTank;


    /**
     * 玩家坦克
     */
    private Map<Integer,ITank> tanks;


    public Map<Integer,ITank> getTanks() {
        return tanks;
    }

    public IPlayer(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void decreaseTank(){
        aliveTank --;
    }

    public int getAliveTank() {
        return aliveTank;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Player player;

    public List<Bullet> nextMove(int[][] map, List<Enemy> enemies){
        List<Tank> myTanks = new ArrayList<>();

        List<Bullet> bullets = new ArrayList<>();

        tanks.forEach((id,iTank)->{
            Tank tank = new Tank(iTank.getId(), iTank.getX(), iTank.getY(), iTank.getDirection());
            myTanks.add(tank);
        });
        List<Command> commands = player.getAction(map, myTanks,enemies);

        commands.forEach(command -> {
            command.getId();
            Bullet bullet = tanks.get(command.getId()).doAction(map,command);
            if(bullet != null) {
                bullets.add(bullet);
            }
        });
        return bullets;
    }




    public void generateTanks(Direction direction, int tankNum, Color color){
        this.aliveTank = tankNum;
        tanks = new HashMap<>();
        int width = GameConstants.GAME_PANEL_GRID_COUNT / (tankNum + 1);
        switch (direction){
            //方向为向上，那么生成位置在下方
            case UP:{

                for (int i = 1; i <= tankNum; i++) {
                    ITank tank = new ITank(id,id * 100 + i,
                            color,
                            i * width,
                            GameConstants.GAME_PANEL_GRID_COUNT - 1,
                            direction);
                    tanks.put(id * 100 + i, tank);
                }
                break;
            }
            //方向朝下，那生成位置在上方
            case DOWN:{
                for (int i = 1; i <= tankNum; i++) {
                    ITank tank = new ITank(id,id * 100 + i,
                            color,
                            i * width,
                            0,
                            direction);
                    tanks.put(id * 100 + i, tank);
                }
                break;
            }
            case LEFT:{
                for (int i = 1; i <= tankNum; i++) {
                    ITank tank = new ITank(id,id * 100 + i,
                            color,
                            GameConstants.GAME_PANEL_GRID_COUNT - 1,
                            i * width,
                            direction);
                    tanks.put(id * 100 + i, tank);
                }
                break;
            }
            case RIGHT:{
                for (int i = 1; i <= tankNum; i++) {
                    ITank tank = new ITank(id,id * 100 + i,
                            color,
                            GameConstants.GAME_PANEL_GRID_COUNT - 1,
                            0,
                            direction);
                    tanks.put(id * 100 + i, tank);
                }
                break;
            }

        }
    }
}
