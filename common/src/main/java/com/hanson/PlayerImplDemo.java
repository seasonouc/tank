package com.hanson;

import com.hanson.entity.Command;
import com.hanson.entity.Tank;
import com.hanson.enums.Action;
import com.hanson.enums.Direction;
import com.hanson.enums.StuffType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerImplDemo implements Player {

    public PlayerImplDemo(){}



    @Override
    public List<Command> getAction(StuffType[][] map, List<Tank> my, List<Tank> enemy) {
        Random random = new Random();
        List<Command> commands = new ArrayList<>();

        my.forEach( tank -> {
            Command command = new Command(tank.getId());
            command.setAction(Action.fromAction(random.nextInt(4)));

            commands.add(command);

            if(command.getAction() == Action.Turn){
                command.setDirection(Direction.getDirection(random.nextInt(4)));
            }
        });

        return commands;

    }
}
