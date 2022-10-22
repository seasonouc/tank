package com.hanson.tank.service;

import com.hanson.Player;
import com.hanson.entity.Command;
import com.hanson.entity.Tank;
import com.hanson.tank.aggregate.IPlayer;
import com.hanson.tank.context.GameContext;
import com.hanson.tank.dto.GameData;
import com.hanson.tank.entity.Boom;
import com.hanson.tank.entity.Bullet;
import com.hanson.tank.entity.ITank;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameDataRefresh implements Runnable{
    private GameContext gameContext;

    public GameDataRefresh(GameContext gameContext){
        this.gameContext = gameContext;

    }

    @Override
    public void run() {
        GameData gamedata = gameContext.getGameData();
        JPanel gamePanel = gameContext.getGamePanel();
        while(gamedata.isStart()){
            for (int i = 0; i < gamedata.getPlayers().size(); i++) {
                Player player = gamedata.getPlayers().get(i);
                IPlayer iPlayer = gamedata.getIPlayers().get(i);

                Map<Integer, ITank> iTanks = iPlayer.getTanks();
                List<Tank> tanks = new ArrayList<>();

                iTanks.forEach((id, iTank) -> {
                    Tank tank = new Tank(iTank.isActive(), iTank.getId(), iTank.getX(), iTank.getY(), iTank.getDirection());
                    tanks.add(tank);
                });

                List<Command> commands = player.getAction(null, tanks, null);

                commands.forEach(command -> {
                    command.getId();
                    Bullet bullet = iTanks.get(command.getId()).doAction(command);

                    if (bullet != null) {
                        gamedata.getBullets().add(bullet);
                    }

                });
            }


            gamedata.getBullets().forEach(bullet -> {
                bullet.move();
                gamedata.getIPlayers().forEach(iPlayer -> {
                    iPlayer.getTanks().forEach((id,tank)->{
                        if (bullet.isActive() && tank.isActive() && bullet.getX() == tank.getX() && bullet.getY() == tank.getY()) {
                            bullet.setActive(false);
                            tank.setActive(false);
                            iPlayer.decreaseTank();
                            gamedata.getBooms().add(new Boom(bullet.getX(), bullet.getY()));
                            return;
                        }
                    });
                });

                gamedata.getWalls().forEach(wall -> {
                    if (bullet.isActive() && wall.isActive() && bullet.getX() == wall.getX() && bullet.getY() == wall.getY()) {
                        bullet.setActive(false);
                        wall.setActive(false);
                        gamedata.getBooms().add(new Boom(bullet.getX(), bullet.getY()));
                        return;
                    }
                });
            });


            gamedata.getBooms().forEach(boom -> {
                boom.move();
            });

            //碰撞检测

            gamedata.clearBullets();
            gamedata.clearBoom();


            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gamePanel.repaint();
        }
    }
}
