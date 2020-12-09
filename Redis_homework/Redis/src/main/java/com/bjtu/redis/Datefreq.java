package com.bjtu.redis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datefreq {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
    private Date date;
    private String str_date;

    public String getStr_date(String user_date){
        try {
            this.date = sdf.parse(user_date);
            date.setMinutes(0);
            str_date = sdf.format(date);
            }catch(Exception e){
                str_date=null;
                System.out.println("时间格式有误，标准格式：yyyyMMddHHmm");
        }
        return str_date;
    }

    public String getNext_date(String user_date) {
        try {
            String str = getStr_date(user_date);
            this.date=sdf.parse(str);
            date.setHours(date.getHours()+1);
            str_date=sdf.format(date);
            return str_date;
            }catch(Exception e){
                str_date=null;
                System.out.println("时间格式有误，标准格式：yyyyMMddHHmm");
        }
        return str_date;
    }

    public boolean T_Compare(String s,String e) throws ParseException {
        long c = 0;
        if(s==null && e==null) return true;
        else{
            try{
                Date start = sdf.parse(s);
                Date end=  sdf.parse(e);
                c= end.getTime()-start.getTime();
            }catch (Exception e1){
                c=-1;
            }
            if(c>=0){
                return true;
            }
            else return false;
        }

    }

}
