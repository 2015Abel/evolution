package com.evolution.jedis;

import com.google.common.base.Optional;
import lombok.Getter;
import redis.clients.jedis.JedisCluster;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @description: jedis demo
 * @author: liuzijian
 * @date: 2018-05-23 15:36
 */
public class JedisDemo {

    JedisCluster jedisCluster;

    private static final String LOCK_MSG = "OK";

    private static final Long UNLOCK_MSG = 1L;

    private static final String SET_IF_NOT_EXIST = "NX";    //key不存在时放入
    private static final String SET_WITH_EXPIRE_TIME = "PX";    //毫秒

    private static final int EXPIRE_TIME = 5000;

    @Getter
    class Lock{

        final String key;
        final boolean isLock;
        String val;

        Lock(String key,String val,boolean isLock){
            this.key = key;
            this.val = val;
            this.isLock = isLock;
        }
    }

    public Lock lock(String key){
        String val = System.nanoTime()+"";
        String result = jedisCluster.set(key,val,SET_IF_NOT_EXIST,SET_WITH_EXPIRE_TIME,EXPIRE_TIME);
        return new Lock(key,val,LOCK_MSG.equals(result));
    }

    public boolean unLock(Lock lock){
        lock = Optional.of(lock).get();
        //lua script
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        Object result = null ;
        result = jedisCluster.eval(script, Collections.singletonList(lock.getKey()), Collections.singletonList(lock.getVal()));

        if (UNLOCK_MSG.equals(result)){
            return true ;
        }else {
            return false ;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long time1 = System.nanoTime();
        TimeUnit.MILLISECONDS.sleep(1000L);
        System.out.println(System.nanoTime()-time1);
    }




}
