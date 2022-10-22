package com.hanson.entity;

import java.util.List;

/**
 * @Description
 * @Author bytedance
 * @Date 2022/10/22 6:22 下午
 **/
public class Enemy {

    private int id;

    public List<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
    }

    private List<Tank> tanks;


    public Enemy(int id){
        this.id = id;
    }


}
