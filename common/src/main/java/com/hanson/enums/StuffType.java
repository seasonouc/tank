package com.hanson.enums;

public enum StuffType {
    Tank(1),
    Wall(2),
    None(3);

    int type;

    StuffType(int type) {
        this.type = type;
    }

}
