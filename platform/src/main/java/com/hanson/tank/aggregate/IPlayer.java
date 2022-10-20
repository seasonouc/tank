package com.hanson.tank.aggregate;

import com.hanson.enums.Direction;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.entity.ITank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
     * 玩家坦克
     */
    private List<ITank> tanks;


    public List<ITank> getTanks() {
        return tanks;
    }

    public IPlayer(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void generateTanks(Direction direction, int tankNum, Color color){
        tanks = new ArrayList<>();
        int width = GameConstants.GAME_PIXEL_WIDTH / (tankNum + 1);
        switch (direction){
            //方向为向上，那么生成位置在下方
            case UP:{

                for (int i = 1; i <= tankNum; i++) {
                    ITank tank = new ITank(id *100 + i,
                            color,
                            i*width,
                            GameConstants.GAME_PIXEL_WIDTH -1 ,
                            direction);
                    tanks.add(tank);
                }
                break;
            }
            //方向朝下，那生成位置在上方
            case DOWN:{

            }
            case LEFT:{

            }
            case RIGHT:{

            }

        }
    }
}
