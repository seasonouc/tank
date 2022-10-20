package com.hanson;

import com.hanson.entity.Command;
import com.hanson.entity.Tank;

import java.util.List;

public interface Player {
    /**
     *
     * @param map
     *
     * @return
     */
    List<Command> getAction(int[][] map, List<Tank> my, List<Tank> enemy);
}
