package com.hanson.tank.view;

import com.hanson.tank.context.GameContext;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

public class MenuBar extends JPanel {

    private ButtonGroup bg;

    private int playerNum;

    private List<String> jarList;


    private JTextField playerInput;

    private JButton setPlayer;

    private GameContext gameContext;

    public MenuBar(GameContext gameContext){

        this.gameContext = gameContext;

        playerInput = new JTextField(5);
        playerInput.setVisible(true);


        JButton button = new JButton("选择玩家程序包");
        add(button);

        button.addActionListener(e->{
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);
            chooser.setFileFilter(new FileNameExtensionFilter("jar","jar"));

            int i = chooser.showOpenDialog(MenuBar.this);//在容器中显示文件选择器
            if (i == JFileChooser.APPROVE_OPTION) {//如果在选择器中单击“打开”
                File[] files = chooser.getSelectedFiles();//获取选择器中选中的文件
                gameContext.getGameData().loadPlayer(files);
            }
        });

        JButton startBnt = new JButton("开始游戏");
        add(startBnt);

        startBnt.addActionListener(e->{
            gameContext.start();
        });
    }
}
