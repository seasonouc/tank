package com.hanson;

import com.hanson.entity.Command;
import com.hanson.entity.Enemy;
import com.hanson.entity.Tank;
import com.hanson.enums.StuffType;

import java.util.List;

public interface Player {

    /**
     *
     * @param map 当前地图
     *
     *
     * @param my
     * @return
     */
    List<Command> getAction(int[][] map, List<Tank> myTank,List<Enemy> enemies);


    String getName();

}
