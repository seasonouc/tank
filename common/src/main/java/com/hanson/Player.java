package com.hanson;

import com.hanson.entity.Command;
import com.hanson.entity.Camp;
import com.hanson.entity.Tank;

import java.util.List;

public interface Player {

    /**
     *
     * @param map 当前地图, 0 代表空地，1代表坦克，2代表砖块，3，代表子弹，3，代表正在爆炸，5代表铁墙，6 代表老家
     * @param my 我方阵营信息
     * @param enemies 敌方阵营信息
     * @return
     */
    List<Command> getAction(int[][] map, Camp my,List<Camp> enemies);


    /**
     *
     * @return  比赛团队名称 ，给你的团队起个名字吧
     */
    String getName();

}
