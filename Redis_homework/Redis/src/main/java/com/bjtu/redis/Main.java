package com.bjtu.redis;

import com.bjtu.redis.filelistener.FileListener;
import com.bjtu.redis.filelistener.FileMonitor;

import java.util.Scanner;

/*
说明：
1. 实现了对Redis 常用操作的封装（JedisDef：1.String 2.list 3.set 4.zset 5.hash）
2. 实现了counter和action的json文件定义（resources），并实现了通过读取json文件实现num计数功能和freq统计功能（以小时为周期）
3. 实现了在程序不停止运行的条件下自适应json文件的变化（保存文件后自动重新读取，filelistener）
 */

public class Main {
    private  static  JsonRead jsonRead;
    private static UseCounter useCounter_;
    private static String action_name;

    public static void main(String[] args) throws Exception {

        FileMonitor m = new FileMonitor(1000);//设置监控的间隔时间
        m.monitor("D:\\IdeaProjects\\Redis\\src\\main\\resources", new FileListener());
        m.start();
        boolean f=false;
        System.out.println("开始读取文件.......");
        while(true) {
            show();
            Scanner input = new Scanner(System.in);
            jsonRead=JsonRead.getJsonRead();
            System.out.print("请输入：");
            switch (input.nextInt()) {
                case 1:
                    action_name = "INCR_NUM";
                    break;
                case 2:
                    action_name = "DECR_NUM";
                    break;
                case 3:
                    action_name = "INCR_FREQ";
                    break;
                case 4:
                    action_name = "DECR_FREQ";
                    break;
                case 5:
                    action_name = "SHOW_NUM";
                    break;
                case 6:
                    action_name = "SHOW_FREQ";
                    break;
                case 0:
                    f = true;
                    break;
                default:
                    System.err.println("***请按提示输入！***");
            }
            if(f){
                System.out.println("***已退出***");
                input.close();
                m.stop();
                return;
            }
            useCounter_ =new UseCounter(jsonRead,action_name);
            useCounter_.Use_action();
        }
    }

    private static void show(){
        System.out.println("*****************************************");
        System.out.println("         请选择要运行的Action        ");
        System.out.println("        1.Action\"INCR_NUM\"     ");
        System.out.println("        2.Action\"DECR_NUM\"     ");
        System.out.println("        3.Action\"INCR_FREQ\"     ");
        System.out.println("        4.Action\"DECR_FREQ\"     ");
        System.out.println("        5.Action\"SHOW_NUM\"     ");
        System.out.println("        6.Action\"SHOW_FREQ\"     ");
        System.out.println("        0.退出                     ");
        System.out.println("*****************************************");
    }

}

