package com.hanson.tank.loader;

import com.hanson.Player;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class JarLoader {

    private URLClassLoader urlClassLoader;

    public JarLoader(URL url) throws MalformedURLException {
        this.urlClassLoader = new URLClassLoader(new URL[]{url});
    }

    public Player loadJarAndGetPlayer() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class playerClass = urlClassLoader.loadClass("com.hanson.tank.demo.Play");
        Player player = (Player)playerClass.newInstance();

        System.out.println(player.getName());
        return player;
    }

}
