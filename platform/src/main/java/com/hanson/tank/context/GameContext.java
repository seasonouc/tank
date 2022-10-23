package com.hanson.tank.context;

import com.hanson.enums.GameStatus;
import com.hanson.tank.aggregate.IPlayer;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.dto.GameData;
import com.hanson.tank.service.GameDataRefresh;
import com.hanson.tank.service.PaintService;
import com.hanson.tank.view.GameFrame;
import com.hanson.tank.view.GamePanel;
import com.hanson.tank.view.MenuBar;
import com.hanson.tank.view.resource.Images;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GameContext {
    private static  GameContext gameContext;


    public static GameContext getInstance(){
        if(gameContext == null) {
            synchronized (GameContext.class) {
                if (gameContext == null) {
                    return new GameContext();
                }
            }
        }
        return gameContext;
    }

    private GameData gameData;

    private GamePanel gamePanel;

    private PaintService paintService;

    public static GameContext getGameContext() {
        return gameContext;
    }


    private ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(10,
            20,
            100L,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue(10));

    private GameContext(){

    }



    public void init(){
        GameFrame gameFrame = new GameFrame();
        int size = GameConstants.GAME_PANEL_GRID_COUNT * GameConstants.GRID_WIDTH;

        gameData = new GameData( );


        paintService = new PaintService(gameData);

        gamePanel = new GamePanel(paintService);
        gamePanel.setVisible(true);
        gamePanel.setSize(size,size);

        gameFrame.add(gamePanel, BorderLayout.CENTER);
        gameFrame.setVisible(true);

        MenuBar menuBar = new MenuBar(this);
        menuBar.setVisible(true);
        gameFrame.add(menuBar,BorderLayout.NORTH);

        gameFrame.setSize(size , size + 60);
        menuBar.setSize(40, size);
        gamePanel.setSize(size, size);

        gameFrame.setIconImage(Images.TankImg[0][0]);

        gameData.setGameInformation("Game not Start");
        gamePanel.repaint();
    }

    public void start(){
        getGameData().init();
        getGameData().setStatus(GameStatus.Starting);
        taskExecutor.execute(new GameDataRefresh(this));
    }

    public void stop(){
        getGameData().setStatus(GameStatus.End);
    }


    public GameData getGameData() {
        return gameData;
    }

    public JPanel getGamePanel(){
        return gamePanel;
    }


    public void showInfo(){
        StringBuffer sb = new StringBuffer();

        for (IPlayer iPlayer : gameData.getIPlayers()) {
            sb.append("player ");
            sb.append(iPlayer.getId());
            sb.append(":");
            sb.append(iPlayer.getPlayer().getName());
            sb.append("\n");
        }

        gameData.setGameInformation(sb.toString());

        gamePanel.repaint();
    }
}
