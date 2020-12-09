package com.bjtu.redis.entity;


import java.util.Scanner;
/*
Counter的实体类
 */
public class Counter {
    private String counterName;
    private String counterIndex;
    private String type;
    private String KeyFields;
    private String valueFields;
    private String timeFields;
    private String start_time;
    private String end_time;

    public Counter(String counterName,String counterIndex,String type,String KeyFields,String valueFields,String timeFields){
        super();
        this.counterName=counterName;
        this.counterIndex=counterIndex;
        this.type=type;
        this.KeyFields=KeyFields;
        this.valueFields=valueFields;
        this.timeFields=timeFields;
    }

    public Counter(){
        super();
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getCounterIndex() {
        return counterIndex;
    }

    public void setCounterIndex(String counterIndex) {
        this.counterIndex = counterIndex;
    }

    public String getKeyFields() {
        return KeyFields;
    }

    public void setKeyFields(String keyFields) {
        this.KeyFields = keyFields;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValueFields() {
        return valueFields;
    }

    public void setValueFields(String valueFields) {
        this.valueFields = valueFields;
    }

    public String getTimeFields() {
        return timeFields;
    }

    public void setTimeFields(String timeFields) {
        this.timeFields = timeFields;
    }

    public String getStart_time() {
        if(type.equals("freq")&&timeFields!=null){
            Scanner scan = new Scanner(timeFields);
            if (scan.hasNext()) {
                start_time = scan.next();
            }
            else System.err.println("counter中时间段为空");
        }
        else start_time=null;
        return start_time;
    }

    public String getEnd_time() {
        if(type.equals("freq")&&timeFields!=null){
            Scanner scan = new Scanner(timeFields);
            if (scan.hasNext()) {
                end_time = scan.next();
                while (scan.hasNext()) {
                    end_time = scan.next();
                }
            }
            else System.err.println("counter中时间段为空");
        }
        else end_time=null;
        return end_time;
    }
}
