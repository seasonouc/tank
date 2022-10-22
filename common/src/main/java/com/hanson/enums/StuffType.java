package com.hanson.enums;

public enum StuffType {
    None(0,true),
    Tank(1,false),
    Wall(2,false),
    Bullet(4,true),
    Boom(5,true);

    int type;
    boolean canMulti;

    StuffType(int type,boolean canMulti) {
        this.type = type;
        this.canMulti = canMulti;
    }

    public int getType(){
        return type;
    }

    public boolean isCanMulti(){
        return canMulti;
    }

    public static StuffType fromType(int type) {
        if (type >= 0 && type < StuffType.values().length) {
            return StuffType.values()[type];
        }
        return None;
    }
}
