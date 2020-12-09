package com.bjtu.redis.entity;

import java.util.ArrayList;
/*
Action的实体类
 */
public class Action {
    private String name;
    private ArrayList<Counter> retrieve;
    private ArrayList<Counter> save;

    public Action(String name,ArrayList<Counter> retrieve,ArrayList<Counter> save){
        super();
        this.name=name;
        this.retrieve=retrieve;
        this.save=save;
    }

    public Action(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Counter> getRetrieve() {
        return retrieve;
    }

    public void setSave(ArrayList<Counter>save) {
        this.save = save;
    }

    public ArrayList<Counter> getSave() {
        return save;
    }

    public void setRetrieve(ArrayList<Counter> retrieve) {
        this.retrieve = retrieve;
    }
}
