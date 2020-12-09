package com.bjtu.redis.entity;



import com.bjtu.redis.entity.Counter;

import java.util.ArrayList;

public class CounterArray {
    private ArrayList<Counter> counter;

    public CounterArray(ArrayList<Counter> counter){
        super();
        this.counter=counter;
    }
    public CounterArray(){
        super();
    }
    public ArrayList<Counter> getCounter() {
        return counter;
    }

    public void setCounter(ArrayList<Counter> counter) {
        this.counter = counter;
    }
}
