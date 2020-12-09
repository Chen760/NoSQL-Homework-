package com.bjtu.redis;

import com.bjtu.redis.entity.Action;
import com.bjtu.redis.entity.Counter;
import com.bjtu.redis.jedis.JedisDef;

import java.text.ParseException;
import java.util.ArrayList;

public class UseCounter {
    private ArrayList<Action> action=null;
    private ArrayList<Counter> counter=null;
    private String action_name;
    

    public UseCounter(JsonRead jsonRead, String action_name){
        this.action=jsonRead.getAction();
        this.counter=jsonRead.getCounter();
        this.action_name=action_name;
    }

    public void Use_action() throws ParseException {
        //boolean flag=false;
        ArrayList<Counter> retrieve=null;
        ArrayList<Counter> save=null;


        for(int i=0;i<action.size();i++){
            if(action.get(i).getName().equals(action_name)){
                retrieve=action.get(i).getRetrieve();
                save=action.get(i).getSave();
                break;
            }
        }

        if(retrieve.size()>0) {
            //System.out.println(retrieve.size());
            for (int i = 0; i < retrieve.size(); i++) {
                System.out.println("Action使用"+retrieve.get(i).getCounterName()+"进行读操作");
                Use_counter(retrieve.get(i).getCounterName());
            }
        }
        if(save.size()>0){
            //System.out.println(save.size());
            for(int i=0;i<save.size();i++){
                System.out.println("Action使用"+save.get(i).getCounterName()+"进行写操作");
                Use_counter(save.get(i).getCounterName());
            }
        }
    }
    
    private Counter findcounter(String countername){
        Counter c = null;
        for(int i=0;i<counter.size();i++) {
            //System.out.println("a");
            if (counter.get(i).getCounterName().equals(countername)) {
                c=counter.get(i);
            }
        }
        return c;
    }
    

    private void Use_counter(String countername) throws ParseException {
        JedisDef jedisDef = new JedisDef();
        Counter counter = findcounter(countername);
        Datefreq datefreq = new Datefreq();;
        String counterName=null;
        String counterIndex=null;
        String type = null;
        String KeyFields = null;
        String valueFields = null;
        String timeFields = null;
        String start_time = null;
        String end_time = null;
        Long lo ;
        String date=null;
        String end_date=null;


        if(counter != null) {
            counterName = counter.getCounterName();
            counterIndex = counter.getCounterIndex();
            type = counter.getType();
            KeyFields = counter.getKeyFields();
            valueFields = counter.getValueFields();
            timeFields = counter.getTimeFields();
            start_time = counter.getStart_time();
            end_time = counter.getEnd_time();
            if (datefreq.T_Compare(start_time, end_time)) {
                switch (countername) {
                case "incrUserNum":
                    lo = Long.valueOf(valueFields);
                    System.out.print("提高了" + valueFields + " 当前" + KeyFields + "的值为---->");
                    count_incr(KeyFields, lo, type, timeFields);
                    break;
                case "decrUserNum":
                    lo = Long.valueOf(valueFields);
                    System.out.print("降低了" + valueFields + " 当前" + KeyFields + "的值为---->");
                    count_decr(KeyFields, lo, type, timeFields);
                    break;
                case "showUserNum":
                    System.out.print("当前" + KeyFields + "的值为--->");
                    show(KeyFields, type, timeFields);
                    break;
                case "showUserFreq":
                    date = datefreq.getStr_date(start_time);
                    end_date = datefreq.getStr_date(end_time);
                    if (date == null) break;
                    if (end_date == null) break;
                    System.out.println("当前" + KeyFields + "在" + "\"" + timeFields + "\"" + "的值为:");
                    while (!end_date.equals(date)) {
                        show(KeyFields, type, date);
                        date = datefreq.getNext_date(date);
                    }
                    show(KeyFields, type, end_date);
                    break;
                case "decrUserFreq":
                    lo = Long.valueOf(valueFields);
                    date = datefreq.getStr_date(start_time);
                    end_date = datefreq.getStr_date(end_time);
                    if (date == null) break;
                    if (end_date == null) break;
                    System.out.println("在" + "\"" + timeFields + "\"" + "降低了" + valueFields);
                    System.out.println("当前" + KeyFields + "在" + "\"" + timeFields + "\"" + "的值为:");
                    while (!end_date.equals(date)) {
                        count_decr(KeyFields, lo, type, date);
                        date = datefreq.getNext_date(date);
                    }
                    count_decr(KeyFields, lo, type, end_date);
                    break;
                case "incrUserFreq":
                    lo = Long.valueOf(valueFields);
                    date = datefreq.getStr_date(start_time);
                    end_date = datefreq.getStr_date(end_time);
                    if (date == null) break;
                    if (end_date == null) break;
                    System.out.println("在" + "\"" + timeFields + "\"" + "提高了" + valueFields);
                    System.out.println("当前" + KeyFields + "在" + "\"" + timeFields + "\"" + "的值为:");
                    while (!end_date.equals(date)) {
                        count_incr(KeyFields, lo, type, date);
                        date = datefreq.getNext_date(date);
                    }
                    count_incr(KeyFields, lo, type, end_date);
                    break;
                default:
                    System.out.print("不存在指定的Counter");

            }
             }else System.out.println("***请保证起始时间小于终止时间***");
        }
        else System.out.println("***不能找到Ation中指定的Counter***");


    }

    private void count_incr(String k,long v,String type,String f){
        JedisDef jedisDef = new JedisDef();
        switch (type){
            case "num":
                jedisDef.incr(k,v);
                break;
            case "freq":
                jedisDef.hincr(k,f,v);
                break;
            default:
                System.err.println("未知counter类型");
        }
    }
    private void count_decr(String k,long v,String type,String f){
        JedisDef jedisDef = new JedisDef();
        switch (type){
            case "num":
                jedisDef.decr(k,v);
                break;
            case "freq":
                jedisDef.hdecr(k,f,v);
                break;
            default:
                System.err.println("未知counter类型");
        }
    }
    private void show(String k,String type,String f){
        JedisDef jedisDef = new JedisDef();
        switch (type){
            case "num":
                jedisDef.get(k);
                break;
            case "freq":
                jedisDef.hget(k,f);
                break;
            default:
                System.err.println("未知counter类型");
        }
    }
}


