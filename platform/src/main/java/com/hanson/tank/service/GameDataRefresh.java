package com.hanson.tank.service;

import com.hanson.Player;
import com.hanson.entity.Command;
import com.hanson.entity.Tank;
import com.hanson.tank.aggregate.IPlayer;
import com.hanson.tank.context.GameContext;
import com.hanson.tank.dto.GameData;
import com.hanson.tank.entity.Bullet;
import com.hanson.tank.entity.ITank;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            for (int i = 0; i < iPlayers.size(); i++) {
                Player player = iPlayers.get(i);
                IPlayer iPlayer = gamedata.getPlayers().get(i);

                Map<Integer,ITank> iTanks = iPlayer.getTanks();
                List<Tank> tanks = new ArrayList<>();

                iTanks.forEach(( id,iTank) -> {
                    Tank tank = new Tank(iTank.isAlive(),iTank.getId(),iTank.getX(),iTank.getY(),iTank.getDirection());
                    tanks.add(tank);
                });

                List<Command> commands = player.getAction(null,tanks,null);

                commands.forEach(command ->{
                    command.getId();
                    Bullet bullet = iTanks.get(command.getId()).doAction(command);

                    if(bullet != null){
                        gamedata.getBullets().add(bullet);
                    }

                });

                gamedata.getBullets().forEach(bullet -> {
                    boolean moveRes = bullet.move();
                });

            }


            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gamePanel.repaint();
        }
    }
}
