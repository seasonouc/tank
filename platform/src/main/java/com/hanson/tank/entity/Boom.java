package com.hanson.tank.entity;

import com.hanson.enums.StuffType;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.view.resource.Images;

import java.awt.*;

public class Boom extends Stuff{

    private int step;

    public Boom(int x,int y){
        super(x,y,true, StuffType.Boom);
        step = 0;
    }

    public void move(){
        step ++;
        if(step >= 5){
            setActive(false);
        }
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
        if(step < 5){
            return Images.bomb[step];
        }
        return Images.bomb[4];
    }

    @Override
    public int getWidth() {
        return GameConstants.GRID_WIDTH;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setColor(Color color) {

    }
}
