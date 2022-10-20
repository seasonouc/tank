package com.hanson.enums;

public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);
    private int dir;
    Direction(int dir){
        this.dir = dir;
    }

    public int getDir(){
        return dir;
    }
}
