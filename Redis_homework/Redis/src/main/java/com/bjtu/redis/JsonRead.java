package com.bjtu.redis;

import com.alibaba.fastjson.JSON;
import com.bjtu.redis.entity.Action;
import com.bjtu.redis.entity.ActionArray;
import com.bjtu.redis.entity.Counter;
import com.bjtu.redis.entity.CounterArray;


import java.io.*;
import java.util.ArrayList;

public class JsonRead {
    private ArrayList<Action> action=null;
    private ArrayList<Counter> counter=null;

    private JsonRead(){}


    static enum SingletonEnum{
        INSTANCE;
        private JsonRead jsonRead;

        private SingletonEnum(){
            jsonRead = new JsonRead();
            jsonRead.setJsonRead();
        }
        public JsonRead getInstance(){
            return jsonRead;
        }
    }
    public static JsonRead getJsonRead(){
        return SingletonEnum.INSTANCE.getInstance();
    }

    public void setJsonRead(){
        System.out.println("读取文件成功！");
        readJsonFile("D:\\IdeaProjects\\Redis\\src\\main\\resources\\counter.json","counter");
        readJsonFile("D:\\IdeaProjects\\Redis\\src\\main\\resources\\action.json","action");
    }


    private void readJsonFile(String filepath, String type) {
        StringBuffer strbuffer = new StringBuffer();
        File myFile = new File(filepath);
        if (!myFile.exists()) {
            System.err.println("Can't Find " + filepath);
        }
        try {
            FileInputStream fis = new FileInputStream(filepath);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
            BufferedReader in  = new BufferedReader(inputStreamReader);

            String str;
            while ((str = in.readLine()) != null) {
                strbuffer.append(str);
                //System.err.println(str);
            }
            in.close();
            inputStreamReader.close();
            fis.close();

            if(type.equals("counter")) {

                CounterArray counterArray = JSON.parseObject(String.valueOf(strbuffer), CounterArray.class);
                ArrayList<Counter> arr = counterArray.getCounter();
                counter = arr;
            }
            else {
                ActionArray actionArray = JSON.parseObject(String.valueOf(strbuffer), ActionArray.class);
                ArrayList<Action> arr=actionArray.getAction();
                action = arr;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Action> getAction() {
        return action;
    }

    public ArrayList<Counter> getCounter() {
        return counter;
    }

}
