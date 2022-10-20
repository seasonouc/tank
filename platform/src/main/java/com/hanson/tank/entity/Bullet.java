package com.hanson.tank.entity;

import com.hanson.enums.Direction;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.view.resource.Images;

import java.awt.*;

public class Bullet extends Stuff{

    private Direction direction;

    public Bullet(int x, int y, Direction direction){
        super(x,y,true);
        this.direction = direction;
    }

    public boolean move(){
        int x = getX() + direction.getMoveX();
        int y = getY() + direction.getMoveY();

        if (x < 0 || y < 0 || x >= GameConstants.GAME_PANEL_GRID_COUNT || y >= GameConstants.GAME_PANEL_GRID_COUNT) {
            setActive(false);
            return false;
        }
        setXY(x,y);
        return true;
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
        return Images.bullet;
    }

    @Override
    public int getWidth() {
        return GameConstants.GRID_WIDTH / 2;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setColor(Color color) {

    }
}
