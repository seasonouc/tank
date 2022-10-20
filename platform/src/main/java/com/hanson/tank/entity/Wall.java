package com.hanson.tank.entity;

import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.view.resource.Images;

import java.awt.*;

public class Wall extends Stuff{

    public Wall(int x,int y){
        super(x,y,true);
    }

    @Override
    public int getXPixel() {
        return 0;
    }

    @Override
    public int getYPixel() {
        return 0;
    }

    @Override
    public Image getImage() {
        return Images.stuffImg[0];
    }

    @Override
    public int getWidth() {
        return GameConstants.GRID_WIDTH / 2;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setColor(Color color) {

    }
}
