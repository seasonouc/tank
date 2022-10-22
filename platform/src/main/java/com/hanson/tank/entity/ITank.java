package com.hanson.tank.entity;

import com.hanson.entity.Command;
import com.hanson.enums.Direction;
import com.hanson.enums.StuffType;
import com.hanson.tank.aggregate.IPlayer;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.view.resource.Images;

import java.awt.*;

public class ITank extends Stuff{


    /**
     * 坦克颜色
     */
    private Color color;

    /**
     * 坦克速度
     */
    private int speed = 4;


    public int getId() {
        return id;
    }

    /**
     * 坦克id
     */
    private int id;

    /**
     * 玩家id
     */
    private int playerId;


    public Direction getDirection() {
        return direction;
    }

    private Direction direction;

    public ITank(int playerId,int id,  Color color, int x, int y, Direction direction){

        super(x,y,true, StuffType.Tank);

        this.id = id;
        this.color = color;
        this.playerId = playerId;

        this.direction = direction;
    }

    public Bullet doAction(int[][] map, Command command) {
        if (!isActive() || command == null) {
            return null;
        }
        switch (command.getAction()){
            case Move:{
                int x = getX() +  direction.getMoveX();
                int y = getY() +  direction.getMoveY();
                if (x < 0 || y < 0 || x >= GameConstants.GAME_PANEL_GRID_COUNT || y >= GameConstants.GAME_PANEL_GRID_COUNT) {
                    break;
                }
                if (StuffType.fromType(map[x][y]).isCanMulti()){
                    setXY(x,y);
                }
                break;
            }
            case Turn:{
                this.direction = command.getDirection();
                break;
            }
            case Fire:{
                return new Bullet(getX(),getY(),direction);
            }
            case DoNothing:{

            }
        }
        return null;
    }

    @Override
    public int getXPixel() {
        return getX() * GameConstants.GRID_WIDTH;
    }

    @Override
    public int getYPixel() {
        return getY() * GameConstants.GRID_WIDTH;
    }

    @Override
    public Image getImage() {
        return Images.TankImg[playerId][direction.getDir()];
    }

    @Override
    public int getWidth() {
        return GameConstants.GRID_WIDTH;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
