package com.evolution.thread.exception;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolExceptionCatcher
 * @Description: 线程池异常捕获demo
 * @Author: Lzj
 * @Date: 2019/4/1 16:41
 */
public class ThreadPoolExceptionCatcher {

    public void handle(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,5,
                5,TimeUnit.SECONDS,new ArrayBlockingQueue<>(10000));

        for (int i=0;i<3;i++){
            executor.execute(new ServiceImplMain());
        }
        executor.shutdown();

    }

    public static void main(String[] args) {
        ThreadPoolExceptionCatcher demo = new ThreadPoolExceptionCatcher();
        demo.handle();
    }
}

class ServiceImplMain implements Runnable{

    @Override
    public void run() {
        try {
            new ServiceImplA().service();
            new ServiceImplB().service();
            new ServiceImplC().service();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("inner class run...");
    }
}


class ServiceImplA{
    public void service(){
        System.out.println("AAAA");
    }
}

class ServiceImplB{
    public void service(){
        System.out.println("要不起");
    }
}

class ServiceImplC{
    public void service(){
        System.out.println(1/0);
    }
}
