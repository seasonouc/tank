package com.hanson.tank.aggregate;

import com.hanson.enums.Direction;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.entity.ITank;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class IPlayer {

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
