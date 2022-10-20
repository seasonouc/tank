package com.hanson.tank.entity;

import com.hanson.enums.Direction;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.view.resource.Images;

import java.awt.*;

public class ITank extends Stuff{


    /**
     * 坦克颜色
     */
    private Color color = Color.GREEN;

    /**
     * 坦克速度
     */
    private int speed = 4;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * 是否存活
     */
    private boolean alive;

    public int getId() {
        return id;
    }

    /**
     * 坦克id
     */
    private int id;


    public Direction getDirection() {
        return direction;
    }

    private Direction direction;

    public ITank(int id, Color color, int x, int y, Direction direction){

        super(x,y);

        this.id = id;
        this.color = color;

        this.direction = direction;
    }

    @Override
    public int getXPixel() {
        int xPixel =  getX() * GameConstants.GRID_WIDTH;
        return xPixel;
    }

    @Override
    public int getYPixel() {
        int yPixel = getY() * GameConstants.GRID_WIDTH;
        return yPixel;
    }

    @Override
    public Image getImage() {
        return Images.TankImg[direction.getDir()];
    }

    @Override
    public int getWidth() {
        return GameConstants.GRID_WIDTH;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
