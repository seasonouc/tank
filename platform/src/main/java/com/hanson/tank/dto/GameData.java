package com.hanson.tank.dto;

import com.hanson.enums.Direction;
import com.hanson.tank.aggregate.IPlayer;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.entity.BattleMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameData {



    /**
     * 玩家列表
     */
    private List<IPlayer> players;

    /**
     * 地图
     */
    private BattleMap battleMap;

    /**
     * 游戏状态
     */
    private boolean start;

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }


    public List<IPlayer> getPlayers() {
        return players;
    }


    public void initData(){
        start = true;
        this.generatePlayers(1);
    }

    public void generatePlayers(int num){

        Direction genDirection[] = new Direction[]{Direction.UP,Direction.DOWN,Direction.LEFT,Direction.RIGHT};

        players = new ArrayList<>();
        for(int i=0;i<num;i++){
            IPlayer player = new IPlayer(i,"default");
            Direction dir = genDirection[i];
            Color color = GameConstants.TANK_COLOR[dir.getDir()];
            player.generateTanks(dir,GameConstants.INIT_TANK_NUM,color);

            players.add(player);
        }
    }

}
