package com.hanson.entity;

import java.util.List;

/**
 * @Description
 * @Author hanson
 * @Date 2022/10/22 6:22 下午
 *
 * 阵营，可表示敌方我和方
 **/
public class Camp {

    private int id;

    public Home getHome() {
        return home;
    }

    private Home home;

    public List<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
    }

    private List<Tank> tanks;


    public Camp(int id,Home home){
        this.id = id;
        this.home = home;
    }


}
