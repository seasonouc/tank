package com.hanson.enums;

public enum StuffType {
    None(0,true),   //空地
    Tank(1,false),  //坦克
    Wall(2,false),  //砖块
    Bullet(3,true),  //子弹
    Boom(4,true),   //爆炸
    Iron(5,false),  //铁墙
    Home(6,false);   //老家

    int type;
    boolean canMulti;  // 表示是否可以重叠

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
