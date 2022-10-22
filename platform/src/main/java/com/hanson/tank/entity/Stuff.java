package com.hanson.tank.entity;

import com.hanson.enums.StuffType;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Stuff {

    public StuffType getStuffType() {
        return stuffType;
    }

    public void setStuffType(StuffType stuffType) {
        this.stuffType = stuffType;
    }

    private StuffType stuffType;

    private int x;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private boolean active;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x,int y){
        this.x = x;
        this.y = y;
    }

    public abstract int getXPixel();

    public abstract int getYPixel();

    private int y;

    public Stuff(int x,int y,boolean active,StuffType stuffTyp){
        this.x = x;
        this.y = y;
        this.active = active;
        this.stuffType = stuffTyp;
    }

    public boolean posEqual(Stuff stuff){
        return this.isActive() & stuff.isActive() & stuff.getX() == this.getX() && stuff.getY() == this.getY();
    }

    public abstract Image getImage();

    public abstract int getWidth();

    public abstract Color getColor();

    public abstract void setColor(Color color);
}
