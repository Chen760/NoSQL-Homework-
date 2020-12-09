package com.bjtu.redis.jedis;


import java.util.List;
import java.util.Map;
import java.util.Set;
public class JedisDef {
    private redis.clients.jedis.Jedis jedis = JedisInstance.getInstance().getResource();


    /*
    Redis对String的基本操作

    set(String k,String v)           给key赋予值value
    setex(String k,int sec,String v) 给key赋予值value同时设置过期时间
    mset(String ... strings)         给多个key赋予对应的value
    incr(String k , long n)              为key的值增加n
    decr(String k,long n)                为key的值减少n
    append(String k, String v)           为key的值后添加字符串
    get(String k)                    得到key的值
    mget(String ... strings)         得到多个key的值
    del(String k)                    删除key
    exists(String k)                  检查是否存在key

    */
    public void set(String k,String v){
        String s;
        s=jedis.set(k, v);
        System.out.println(k + ":" +s);
        jedis.close();

    }

    public void setex(String k,int sec,String v){
        String s;
        s=jedis.setex(k,sec,v);
        System.out.println(k + ":" +s);
        jedis.close();
    }

    public void mset(String ... strings){
        String k,v,s;
        int i=0;
        int length=strings.length;
        if(length%2!=0) System.out.println("参数应该为key1,val1...的格式");
        else {
            while(i<length){
                k=strings[i];
                v=strings[i++];
                s=jedis.set(k,v);
                System.out.println(k + ":" +s);
                i++;
            }
        }
        jedis.close();
    }

    public long incr(String k , long n){
        long l=0;
        if(!jedis.exists(k)) jedis.set(k,"0");
        l=jedis.incrBy(k,n);
        System.out.println(k + ":" +l);
        jedis.close();
        return l;
    }

    public long decr(String k,long n){
        long l=0;
        if(!jedis.exists(k)) jedis.set(k,"0");
        l = Long.parseLong(jedis.get(k));
        if(l-n<0){
            System.out.println("key所存值不能为负数");
        }
        else {
            l=jedis.decrBy(k, n);
            System.out.println(k + ": " + l);
        }
        jedis.close();
        return l;
    }

    public String append(String k, String v){
        String s=null;
        if(!jedis.exists(k)){
            jedis.set(k,"");
        }
        jedis.append(k,v);
        s=jedis.get(k);
        System.out.println("delete: "+s);
        jedis.close();
        return s;
    }

    public String  get(String k){
        String s=null;
        if(jedis.exists(k)){
            s=jedis.get(k);
            System.out.println(k +": " + s);
        }
        else System.out.println("key不存在");
        jedis.close();
        return s;
    }

    public List<String> mget(String ... strings){
        int i=0;
        String k,s;
        List<String> list = null;
        int length=strings.length;
        while(i<length){
            k=strings[i];
            s=jedis.get(k);
            list.add(s);
            System.out.println(k + ":" +s);
            i++;
        }
        jedis.close();
        return list;
    }

    public boolean del(String k){
        long l;
        if(jedis.exists(k)){
            l=jedis.del(k);
            System.out.println("delete: "+l);
            jedis.close();
            return true;
        }
        else {
            System.out.println("key不存在");
            jedis.close();
            return false;
        }
    }

    public boolean exists(String k){
        if(jedis.exists(k)) return true;
        else return false;
    }

    /*
    Redis对list的基本操作

    rpush(String k)                          在k的list尾部添加元素
    lpush(String k)                          在k的list头部添加元素
    rpop(String k)                           删除k的list的尾部
    lpop(String k)                           删除k的list的头部
    lset(String k,int i,String v)            修改k的list的i位的值
    llen(String k)                           获取k的list的长度
    lindex(String k,int i)                   获取k的list的i位的值
    lrange(String k,int start,int end)       获取k的list的部分值
    lrem(String k,String v)                  删除k的list中值为v的元素

     */

    public void rpush(String k){
        jedis.rpush(k);
        System.out.println(lrange(k,0, (int) (llen(k)-1)));
        jedis.close();
    }
    public void lpush(String k){
        jedis.lpush(k);
        System.out.println(lrange(k,0, (int) (llen(k)-1)));
        jedis.close();
    }
    public void rpop(String k){
        jedis.rpop(k);
        System.out.println(lrange(k,0, (int) (llen(k)-1)));
        jedis.close();
    }
    public void lpop(String k){
        jedis.lpop(k);
        System.out.println(lrange(k,0, (int) (llen(k)-1)));
        jedis.close();
    }
    public void lset(String k,int i,String v){
        if(i<llen(k))
           jedis.lset(k,i,v);
        else System.out.println("index="+i+"越界");
        System.out.println(lrange(k,0, (int) (llen(k)-1)));
        jedis.close();
    }
    public Long llen(String k){
        long lo = jedis.llen(k);
        jedis.close();
        return lo;
    }
    public String lindex(String k,int i){
        String s=null;
        if(i<llen(k))
            s=jedis.lindex(k,i);
        else System.out.println("index="+i+"越界");
        jedis.close();
        return s;
    }
    public List<String> lrange(String k,int start,int end){
        List<String> list=null;
        if(end<llen(k))
            list=jedis.lrange(k,start,end);
        else System.out.println("查找范围越界越界");
        jedis.close();
        return list;

    }

    public void lrem(String k,String v){
        jedis.lrem(k,0,v);
        System.out.println(lrange(k,0, (int) (llen(k)-1)));
        jedis.close();
    }


    /*
    Redis对set的基本操作
    sadd(String k,String m)           向k的set中添加成员m
    srem(String k,String m)           删除k的set中的成员m
    scard(String k)                   统计k的set中成员个数
    sismember(String k,String m)      判断成员m是否在k的set中
    smembers(String k)                获取k的set
     */

    public void sadd(String k,String m){
        jedis.sadd(k,m);
        System.out.println(jedis.smembers(k));
        jedis.close();
    }
    public void srem(String k,String m){
        jedis.srem(k);
        System.out.println(jedis.smembers(k));
        jedis.close();
    }
    public long scard(String k){
        long lo = jedis.scard(k);
        jedis.close();
        return  lo;
    }
    public boolean sismember(String k,String m){
        if(jedis.sismember(k,m)) return true;
        else  return false;
    }
    public Set<String> smembers(String k){
        Set<String> set = jedis.smembers(k);
        jedis.close();
        return set;
    }

    /*
    Redis对zset的基本操作
    zadd(String k,double s,String m)      向k的zset添加成员m
    zrem(String k,String m)               删除k的zset中的成员
    zincrby(String k,int incr,String m)   提高k的zset的score
    zcard(String k)                       获取k的zset的成员个数
    zrank(String k,String m)              获取成员m在zset中从小到大的排名
    zrevrank(String k,String m)           获取成员m在zset中从大到小的排名
     */
    public void zadd(String k,double s,String m){
        jedis.zadd(k,s,m);
        jedis.close();
    }
    public void zrem(String k,String m){
        jedis.zrem(k,m);
        jedis.close();
    }
    public void zincrby(String k,int incr,String m){
        jedis.zincrby(k,incr,m);
        jedis.close();
    }
    public long zcard(String k){
        long lo =jedis.zcard(k);
        jedis.close();
        return lo;
    }
    public long zrank(String k,String m){
        long lo = jedis.zrank(k,m);
        jedis.close();
        return lo;
    }
    public long zrevrank(String k,String m){
        long lo = jedis.zrevrank(k,m);
        jedis.close();
        return lo;
    }
    /*
    Redis对hash的基本操作
    hset(String k,String f,String v）         设置k的hash中f对应的值为v
    hget(String k,String f)                   获取k的hash中f的值
    hmset(String k,Map<String,String> map)    给k的hash赋值
    hmget(String k,String .., fields)         获取k的hash中多个值
    hexists((String k,String f)               判断f是否属于k的hash
    hdel(String k,String f)                   删除k的hash中的f
    hlen(Sting k)                             获取k的hash的元素个数
    hgetAll(String k)                         获取k的hash
    hincr(String k,String f,long n)           k的hash中f对应的值加n
    hdecr(String k,String f,long n)           k的hash中f对应的值减n
     */
    public void hset(String k,String f,String v){
        jedis.hset(k,f,v);
        jedis.close();
    }
    public String hget(String k,String f) {
        String s=null;
        if(jedis.hexists(k,f)) {
            s= jedis.hget(k,f);
            System.out.println(f + ": " +s);
        }
        else System.out.println("hash中无指定的元素");
        jedis.close();
        return s;
    }
    public void hmset(String k,Map<String,String> map) {
        jedis.hmset(k,map);
        jedis.close();
    }
    public List<String> hmget(String k,String ... fields) {
        List<String> list=null;
        for(int i=0;i<fields.length;i++){
            if(jedis.hexists(k,fields[i]))
                list.add(jedis.hget(k,fields[i]));
            else
                System.out.println(fields[i]+"不存在");
        }
        jedis.close();
        return list;
    }
    public boolean hexists(String k,String f)  {
        if(jedis.hexists(k,f))return true;
        else return false;
    }
    public boolean hdel(String k,String f) {
        if(jedis.hexists(k,f)) {
            jedis.hdel(k,f);
            jedis.close();
            return true;
        }
        else {
            System.out.println(f+"不存在");
            jedis.close();
            return false;
        }
    }
    public long hlen(String k) {
        long lo=jedis.hlen(k);
        jedis.close();
        return lo;
    }
    public Map<String,String> hgetAll(String k){
        Map<String,String> map = jedis.hgetAll(k);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        jedis.close();
        return map;
    }

    public long hincr(String k ,String f, long n){
        long l=0;
        if(!jedis.hexists(k,f)) jedis.hset(k,f,"0");
        l=jedis.hincrBy(k,f,n);
        System.out.println(f + ":" +l);
        jedis.close();
        return l;
    }

    public long hdecr(String k,String f,long n){
        long l=0;
        if(!jedis.hexists(k,f)) jedis.hset(k,f,"0");
        l = Long.parseLong(jedis.hget(k,f));
        if(l-n<0){
            System.out.println("\""+f+"\""+"所存值不能为负数");
        }
        else {
            l=jedis.hincrBy(k,f, -n);
            System.out.println(f + ": " + l);
        }
        jedis.close();
        return l;
    }
}
