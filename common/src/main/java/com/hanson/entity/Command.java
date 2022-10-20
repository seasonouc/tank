package com.hanson.entity;

import com.hanson.enums.Action;

/**
 * 指令
 */
public class Command {

    private int id;

    private Action action;

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
