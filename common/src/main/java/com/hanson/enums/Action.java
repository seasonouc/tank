package com.hanson.enums;

public enum Action {

    Move(0), //向当前方向移动一个单位
    Turn(1),  //调转一个方向
    Fire(2),  //向前方开火
    DoNothing(3);  //停留在原地，什么也不做

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
