package com.hanson.tank.entity;

import com.hanson.enums.StuffType;
import com.hanson.tank.constants.GameConstants;
import com.hanson.tank.view.resource.Images;

import java.awt.*;

/**
 * @Description
 * @Author hanson
 * @Date 2022/10/23 12:16 上午
 **/
public class Iron extends Stuff {


    public Iron(int x, int y) {
        super(x, y, true, StuffType.Iron);
    }

    @Override
    public int getXPixel() {
        return getX() * GameConstants.GRID_WIDTH;
    }

    @Override
    public int getYPixel() {
        return getY() * GameConstants.GRID_WIDTH;
    }

    @Override
    public Image getImage() {
        return Images.iron;
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
