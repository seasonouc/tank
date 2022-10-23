package com.hanson.tank.service;

import com.hanson.entity.Camp;
import com.hanson.entity.Home;
import com.hanson.entity.Tank;
import com.hanson.enums.GameStatus;
import com.hanson.tank.aggregate.IPlayer;
import com.hanson.tank.context.GameContext;
import com.hanson.tank.dto.GameData;
import com.hanson.tank.entity.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameDataRefresh implements Runnable {
    private GameContext gameContext;

    public GameDataRefresh(GameContext gameContext) {
        this.gameContext = gameContext;

    }

    @Override
    public void run() {
        GameData gamedata = gameContext.getGameData();
        JPanel gamePanel = gameContext.getGamePanel();
        while (gamedata.getStatus() == GameStatus.Starting) {
            int[][] map = gamedata.getMap();
            int servyveCount = 0;
            String winner = "";

            for (IPlayer iPlayer : gamedata.getIPlayers()) {
                if (iPlayer.getAliveTank() > 0) {
                    servyveCount++;
                    winner = new String(iPlayer.getPlayer().getName());
                }

                int[][] tmp = new int[map.length][];

                for (int i = 0; i < map.length; i++) {
                    tmp[i] = new int[map[i].length];
                    System.arraycopy(map[i], 0, tmp[i], 0, map[i].length);
                }

                List<Camp> enemies = new ArrayList<>();

                for (IPlayer otherPlayer : gamedata.getIPlayers()) {
                    if (otherPlayer.getId() != iPlayer.getId()) {
                        Camp camp = new Camp(otherPlayer.getId(), new Home(otherPlayer.getiHome().getX(), otherPlayer.getiHome().getY()));
                        enemies.add(camp);

                        List<Tank> enemyTank = new ArrayList<>();
                        otherPlayer.getTanks().forEach((id, iTank) -> {
                            if (iTank.isActive()) {
                                enemyTank.add(new Tank(iTank.getId(), iTank.getX(), iTank.getY(), iTank.getDirection()));
                            }
                        });

                        camp.setTanks(enemyTank);
                    }
                }

                List<Bullet> outBullets = iPlayer.nextMove(tmp,map, enemies);
                gamedata.getBullets().addAll(outBullets);
            }

            if (servyveCount == 1) {
                gamedata.setGameInformation("player: " + winner + " wins");
                gamedata.setStatus(GameStatus.End);
            } else if (servyveCount == 0) {
                gamedata.setGameInformation("Game Over,No Winners");
                gamedata.setStatus(GameStatus.End);
            }

            List<Bullet> bullets = gamedata.getBullets();

            for (Bullet bullet : bullets) {
                bullet.move();
                boolean hit = false;

                for (IPlayer iPlayer : gamedata.getIPlayers()) {
                    for (ITank tank : iPlayer.getTanks().values()) {
                        if (bullet.posEqual(tank)) {
                            bullet.setActive(false);
                            tank.setActive(false);
                            iPlayer.decreaseTank();
                            gamedata.getBooms().add(new Boom(bullet.getX(), bullet.getY()));
                            hit = true;
                            break;
                        }
                    }
                    IHome home = iPlayer.getiHome();

                    if (bullet.posEqual(home)) {
                        bullet.setActive(false);
                        iPlayer.homeDie();
                        gamedata.getBooms().add(new Boom(bullet.getX(), bullet.getY()));
                        for (ITank tank : iPlayer.getTanks().values()) {
                            if (tank.isActive()) {
                                gamedata.getBooms().add(new Boom(tank.getX(), tank.getY()));
                            }
                        }
                        hit = true;
                    }
                    if (hit) {
                        break;
                    }
                }
                if (hit) {
                    continue;
                }

                for (Wall wall : gamedata.getWalls()) {
                    if (bullet.posEqual(wall)) {
                        bullet.setActive(false);
                        wall.setActive(false);
                        gamedata.getBooms().add(new Boom(bullet.getX(), bullet.getY()));
                        hit = true;
                        break;
                    }
                }
                if (hit) {
                    continue;
                }


                gamedata.getIrons().forEach(iron -> {
                    if (bullet.posEqual(iron)) {
                        bullet.setActive(false);
                        return;
                    }
                });

            }


            for (int i = 0; i < bullets.size() - 1; i++) {
                if (!bullets.get(i).isActive()) {
                    continue;
                }
                for (int j = i + 1; j < bullets.size(); j++) {
                    if (!bullets.get(j).isActive()) {
                        continue;
                    }
                    if (bullets.get(i).posEqual(bullets.get(j))) {
                        bullets.get(i).setActive(false);
                        bullets.get(j).setActive(false);
                    }
                }
            }

            for (Boom boom : gamedata.getBooms()) {
                boom.move();
            }

            //碰撞检测
            gamedata.clearBullets();
            gamedata.clearBoom();
            gamedata.refreshMap();

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gamePanel.repaint();
        }
    }
}
