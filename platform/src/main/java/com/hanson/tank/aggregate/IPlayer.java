package com.hanson.tank.aggregate;

import com.hanson.Player;
import com.hanson.entity.Command;
import com.hanson.entity.Camp;
import com.hanson.entity.Home;
import com.hanson.entity.Tank;
import com.hanson.enums.Direction;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.entity.Bullet;
import com.hanson.tank.entity.IHome;
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

    public IHome getiHome() {
        return iHome;
    }

    /**
     * 老家
     */
    private IHome iHome;


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

    public IPlayer(int id){
        this.id = id;
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

    public List<Bullet> nextMove(int[][] tmpMap,int [][] innerMap, List<Camp> enemies){
        List<Tank> myTanks = new ArrayList<>();

        List<Bullet> bullets = new ArrayList<>();

        tanks.forEach((id,iTank)->{
            if(iTank.isActive()) {
                Tank tank = new Tank(iTank.getId(), iTank.getX(), iTank.getY(), iTank.getDirection());
                myTanks.add(tank);
            }
        });
        Home home = new Home(iHome.getX(),iHome.getY());
        Camp my = new Camp(this.id,home);
        my.setTanks(myTanks);

        List<Command> commands = player.getAction(tmpMap, my,enemies);

        commands.forEach(command -> {
            command.getId();
            Bullet bullet = tanks.get(command.getId()).doAction(innerMap,command);
            if(bullet != null) {
                bullets.add(bullet);
            }
        });
        return bullets;
    }

    public void homeDie(){
        iHome.setActive(false);
        tanks.forEach((id,tank)->{
            tank.setActive(false);
        });
        aliveTank = 0;
    }

     public void generateHome(Direction direction){
        int middle = GameConstants.GAME_PANEL_GRID_COUNT /2;

        switch (direction){
            case UP:{
                iHome = new IHome(middle, GameConstants.GAME_PANEL_GRID_COUNT - 1);
                break;
            }
            case DOWN:{
                iHome = new IHome(middle, 0);
                break;
            }
            case LEFT:{
                iHome = new IHome(GameConstants.GAME_PANEL_GRID_COUNT - 1,middle);
                break;
            }
            case RIGHT:{
                iHome = new IHome(0,middle);
            }
        }
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
                            GameConstants.GAME_PANEL_GRID_COUNT - 2,
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
                            1,
                            direction);
                    tanks.put(id * 100 + i, tank);
                }
                break;
            }
            case LEFT:{
                for (int i = 1; i <= tankNum; i++) {
                    ITank tank = new ITank(id,id * 100 + i,
                            color,
                            GameConstants.GAME_PANEL_GRID_COUNT - 2,
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
                            1,
                            direction);
                    tanks.put(id * 100 + i, tank);
                }
                break;
            }

        }
    }
}
