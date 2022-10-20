package com.hanson.tank.service;

import com.hanson.Player;
import com.hanson.entity.Tank;
import com.hanson.tank.aggregate.IPlayer;
import com.hanson.tank.context.GameContext;
import com.hanson.tank.dto.GameData;
import com.hanson.tank.entity.ITank;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameDataRefresh implements Runnable{
    private GameContext gameContext;

    private List<Player> iPlayers;

    public GameDataRefresh(GameContext gameContext, List<Player> iPlayers){
        this.gameContext = gameContext;
        this.iPlayers = iPlayers;
    }

    @Override
    public void run() {
        GameData gamedata = gameContext.getGameData();
        JPanel gamePanel = gameContext.getGamePanel();
        while(gamedata.isStart()){
//            for (int i = 0; i < iPlayers.size(); i++) {
//                Player player = iPlayers.get(i);
//                IPlayer iPlayer = gamedata.getPlayers().get(i);
//
//                List<ITank> iTanks = iPlayer.getTanks();
//
//                List<Tank> tanks = new ArrayList<>();
//
//            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gamePanel.repaint();
        }
    }
}
