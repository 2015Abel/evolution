package com.evolution.gc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CMSGCLogs
 * @Description: TODO
 * @Author: Lzj
 * @Date: 2019/9/6 15:36
 */
public class CMSGCLogs {
    //-Xmx10m -Xms10m -Xmn4M -XX:+PrintGC -XX:+UseConcMarkSweepGC
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque list = new LinkedBlockingDeque();

        ExecutorService es = Executors.newFixedThreadPool(1);
        boolean flag = true;
        while (flag){
//            TimeUnit.MILLISECONDS.sleep(100L);    //## 位置1：休息100ms
//            System.out.println(String.format("list.size():%d",list.size()));
            es.execute(()->{
                try {
                    byte[] bytes = new byte[1024*1000*1];
                    TimeUnit.MILLISECONDS.sleep(50L);
//                    bytes[0] = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        es.shutdown();
    }
}
