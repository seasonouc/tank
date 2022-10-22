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

    public static int DIR_STEP[][] = {{0,-1},{1,0},{0,1},{-1,0}};

    public int getDir(){
        return dir;
    }

    public int getMoveX(){
        return DIR_STEP[dir][0];
    }

    public int getMoveY(){
        return DIR_STEP[dir][1];
    }

    public static  Direction getDirection(int dir){
        if (dir <= Direction.values().length - 1 && dir >= 0){
            return Direction.values()[dir];
        }
        return UP;
    }

}
