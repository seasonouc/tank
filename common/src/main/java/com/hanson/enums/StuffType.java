package com.hanson.enums;

public enum StuffType {
    Tank(1),
    Wall(2);

    int type;

    StuffType(int type) {
        this.type = type;
    }

}
