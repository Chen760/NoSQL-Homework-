package com.bjtu.redis.entity;

import com.bjtu.redis.entity.Action;

import java.util.ArrayList;

public class ActionArray {
    private ArrayList<Action> action;

    public ActionArray(ArrayList<Action> action){
        this.action=action;
    }

    public ArrayList<Action> getAction() {
        return action;
    }

    public void setAction(ArrayList<Action> action) {
        this.action = action;
    }
}
