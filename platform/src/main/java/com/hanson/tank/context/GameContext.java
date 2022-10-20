package com.hanson.tank.context;

import com.hanson.Player;
import com.hanson.PlayerImplDemo;
import com.hanson.enums.StuffType;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.dto.GameData;
import com.hanson.tank.service.GameDataRefresh;
import com.hanson.tank.service.PaintService;
import com.hanson.tank.view.GameFrame;
import com.hanson.tank.view.GamePanel;
import com.hanson.tank.view.resource.Images;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
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

    private StuffType[][] map;

    private ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(10,
            20,
            100L,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue(10));

    private GameContext(){

    }



    public void init(){
        GameFrame gameFrame = new GameFrame();

        gameData = new GameData( );
        gameData.generatePlayers(2);

        paintService = new PaintService(gameData);

        gamePanel = new GamePanel(paintService);
        gamePanel.setVisible(true);
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);

        int size = GameConstants.GAME_PANEL_GRID_COUNT * GameConstants.GRID_WIDTH;

        gameFrame.setSize(size+30,size+30);
        gamePanel.setSize(size,size);

        gameFrame.setIconImage(Images.TankImg[0]);
        gameData.setStart(true);

        List<Player> players = new ArrayList<>();
        players.add(new PlayerImplDemo());
        players.add(new PlayerImplDemo());

        taskExecutor.execute(new GameDataRefresh(this,players));
    }

    public GameData getGameData() {
        return gameData;
    }

    public JPanel getGamePanel(){
        return gamePanel;
    }


    public void resetMap(){
        map = new StuffType[GameConstants.GAME_PANEL_GRID_COUNT][GameConstants.GAME_PANEL_GRID_COUNT];

    }

}
