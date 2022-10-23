package com.hanson.tank.view.resource;

import java.awt.*;


/**
 * @Description
 * @Author hanson
 * @Date 2022/10/20 11:31 下午
 **/

public class Images {
    /**
     * 我方坦克图片
     */
    public static final Image[][] TankImg = {
            {
                getImage("/img/UTank_.gif"),
                getImage("/img/RTank_.gif"),
                getImage("/img/DTank_.gif"),
                getImage("/img/LTank_.gif")
            },
            {
                getImage("/img/UTank.gif"),
                getImage("/img/RTank.gif"),
                getImage("/img/DTank.gif"),
                getImage("/img/LTank.gif")
            },
            {
                getImage("/img/p1tank.gif"),
                getImage("/img/p1tankR.gif"),
                getImage("/img/p1tankD.gif"),
                getImage("/img/p1tankL.gif")
            }
    };


    /**
     * 地图上的物体图片（砖块、铁块、河流）
     */
    public static final Image[] stuffImg = {
            getImage("/img/brick.png"),
            getImage("/img/iron.gif"),
            getImage("/img/water.gif")
    };

    /**
     * 爆炸图片
     */
    public static final Image[] bomb = {
            getImage("/img/bomb_1.png"),
            getImage("/img/bomb_2.png"),
            getImage("/img/bomb_3.png"),
            getImage("/img/bomb_4.png"),
            getImage("/img/bomb_5.png")
    };

    /**
     * 老家图片
     */
    public static final Image home = getImage("/img/home.png");

    /**
     * 子弹图片
     */
    public static final Image bullet = getImage("/img/bullet.gif");
    /**
     * 游戏开始背景图
     */
    public static final Image startImage = getImage("/img/gameStart.png");
    /**
     * 游戏失败
     */
    public static final Image gameOver = getImage("/img/gameOver.gif");
    /**
     * 游戏成功
     */
    public static final Image gameWin = getImage("/img/gameWin.gif");
    /**
     * 笑脸1
     */
    public static final Image yctSmile1 = getImage("/img/yct1.png");
    /**
     * 笑脸2
     */
    public static final Image yctSmile2 = getImage("/img/yct2.png");

    /**
     * 铁墙
     */
    public static final Image iron = getImage("/img/iron.gif");
    /**
     * 游戏开始动态文字
     */
    public static final Image font = getImage("/img/font.png");

    private static Image getImage(String name) {
        return Toolkit.getDefaultToolkit().getImage(Images.class.getResource(name));
    }
}


