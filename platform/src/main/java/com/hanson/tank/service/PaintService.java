package com.hanson.tank.service;


import com.hanson.tank.aggregate.IPlayer;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.context.GameContext;
import com.hanson.tank.dto.GameData;
import com.hanson.tank.entity.Bullet;
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
        g.setColor(Color.BLACK);
        g.fillRect(0,0, GameConstants.GRID_WIDTH*GameConstants.GAME_PANEL_GRID_COUNT,GameConstants.GRID_WIDTH*GameConstants.GAME_PANEL_GRID_COUNT);

        if(gameData.isStart()){

            List<IPlayer> players = gameData.getIPlayers();

            players.forEach(player -> {
                player.getTanks().forEach((id,tank) -> {
                    drawStuff(panel,g,tank);
                });
            });

            List<Bullet> bullets =  gameData.getBullets();

            for(Bullet bullet:bullets){
                drawStuff(panel,g,bullet);
            }

            gameData.getBooms().forEach(boom -> drawStuff(panel,g,boom));
            gameData.getWalls().forEach(wall -> drawStuff(panel,g,wall));
        }else{
            drawText(panel,g,gameData.getGameInformation());
        }

    }

    public void  drawStuff(JPanel panel, Graphics g, Stuff stuff){
        if( !stuff.isActive()){
            return;
        }

        g.drawImage(stuff.getImage(),
                stuff.getXPixel(),
                stuff.getYPixel(),
                stuff.getWidth(),
                stuff.getWidth(),
                panel);
    }

    public void drawText(JPanel panel, Graphics g, String text){
        int middle = GameConstants.GAME_PANEL_GRID_COUNT / 2 * GameConstants.GRID_WIDTH;
        int size = GameConstants.GAME_PANEL_GRID_COUNT * GameConstants.GRID_WIDTH;
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.BLUE);
        g.drawString(text,middle,middle);
    }


}
