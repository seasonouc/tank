package com.hanson.entity;

import com.hanson.enums.Action;
import com.hanson.enums.Direction;

/**
 * 指令
 */
public class Command {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private Action action;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private Direction direction;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Command(int id){
        this.id = id;
    }
}
