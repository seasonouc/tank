package com.hanson.tank.entity;

import com.hanson.enums.StuffType;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.view.resource.Images;

import java.awt.*;

/**
 * @Description
 * @Author hanson
 * @Date 2022/10/23 12:10 上午
 **/
public class IHome extends Stuff {


    public IHome(int x, int y) {
        super(x, y, true, StuffType.Home);
    }

    @Override
    public int getXPixel() {
        return this.getX() * GameConstants.GRID_WIDTH;
    }

    @Override
    public int getYPixel() {
        return this.getY() * GameConstants.GRID_WIDTH;
    }

    @Override
    public Image getImage() {
        return Images.home;
    }

    @Override
    public int getWidth() {
        return GameConstants.GRID_WIDTH;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setColor(Color color) {

    }
}
