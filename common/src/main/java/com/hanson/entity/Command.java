package com.hanson.entity;

import com.hanson.enums.Action;
import com.hanson.enums.Direction;


/**
 * 坦克指令
 */
public class Command {

    /**
     * 当前坦克id
     */
    private int id;

    /**
     * 指令
     */
    private Action action;


    public Command(int id){
        this.id = id;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
