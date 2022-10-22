package com.hanson.entity;

import com.hanson.enums.Action;
import com.hanson.enums.Direction;

public class Tank {

    private int x;
    private int y;
    private Direction direction;

    public int getId() {
        return id;
    }

    private int id;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public Tank(int id ,int x, int y, Direction direction){

        this.id = id;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}
