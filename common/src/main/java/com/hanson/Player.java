package com.hanson;

import com.hanson.entity.Command;
import com.hanson.entity.Tank;
import com.hanson.enums.StuffType;

import java.util.List;

public interface Player {
    /**
     *
     * @param map
     *
     * @return
     */
    List<Command> getAction(StuffType[][] map, List<Tank> my, List<Tank> enemy);


}
