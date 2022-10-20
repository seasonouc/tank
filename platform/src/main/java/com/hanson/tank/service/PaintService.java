package com.hanson.tank.service;


import com.hanson.tank.aggregate.IPlayer;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.context.GameContext;
import com.hanson.tank.dto.GameData;
import com.hanson.tank.entity.Stuff;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PaintService {

    private GameData gameData;

    public PaintService(GameData gameData){
        this.gameData = gameData;
    }

    public void paintGamePanel(JPanel panel, Graphics g){
        GameContext context = GameContext.getInstance();

        if(gameData.isStart()){

            List<IPlayer> players = gameData.getPlayers();

            players.forEach(player -> {
                player.getTanks().forEach(tank -> {
                    drawStuff(panel,g,tank);
                });
            });


        }

    }

    public void drawStuff(JPanel panel, Graphics g, Stuff stuff){
        g.drawImage(stuff.getImage(),
                stuff.getXPixel(),
                stuff.getYPixel(),
                stuff.getWidth(),
                stuff.getWidth(),
                panel);
    }


}
