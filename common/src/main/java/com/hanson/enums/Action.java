package com.hanson.enums;

public enum Action {

    Move(0),
    Turn(1),
    Fire(2),
    DoNothing(3);

    int action;

    Action(int action){
        this.action = action;
    }

    public static Action fromAction(int action){
        if (action < 4 & action >= 0) {
            return Action.values()[action];
        }
        return DoNothing;
    }
}
