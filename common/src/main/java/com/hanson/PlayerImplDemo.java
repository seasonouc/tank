package com.hanson;

import com.hanson.entity.Command;
import com.hanson.entity.Tank;
import com.hanson.enums.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerImplDemo implements Player {


    @Override
    public List<Command> getAction(int[][] map, List<Tank> my, List<Tank> enemy) {
        Random random = new Random();
        List<Command> commands = new ArrayList<>();

        my.forEach( tank -> {
            Command command = new Command(tank.getId());
            command.setAction(Action.fromAction(random.nextInt(4)));

            commands.add(command);
        });

        return commands;

    }
}
