package com.hanson.enums;

public enum StuffType {
    None(0),
    Tank(1),
    Wall(2),
    Bullet(4),
    Boom(5);

    int type;

    StuffType(int type) {
        this.type = type;
    }

    public int getType(){
        return type;
    }
}
