package com.hanson.tank.view;

import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.service.PaintService;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {


    private PaintService paintService;

    public GamePanel(PaintService paintService) {
        this.paintService = paintService;
        int size = GameConstants.GAME_PIXEL_WIDTH * GameConstants.GRID_WIDTH;
        setSize(size, size);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.paintService.paintGamePanel(this, g);
     }

}
