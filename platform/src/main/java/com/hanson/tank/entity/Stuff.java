package com.hanson.tank.entity;

import com.hanson.enums.StuffType;

import java.awt.*;

public abstract class Stuff {

    public StuffType getStuffType() {
        return stuffType;
    }

    public void setStuffType(StuffType stuffType) {
        this.stuffType = stuffType;
    }

    private StuffType stuffType;

    private int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract int getXPixel();

    public abstract int getYPixel();

    private int y;

    public Stuff(int x,int y){
        this.x = x;
        this.y = y;
    }

    public abstract Image getImage();

    public abstract int getWidth();

    public abstract Color getColor();
}
