package com.evolution.thread;

import lombok.Getter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName CallbackTest
 * @Description: TODO
 * @Author: Lzj
 * @Date: 2019/8/28 9:52
 */
public class CallbackTest {
    class Task implements Runnable{

        @Getter
        Object result;

        // 构造函数传入调用线程（main线程）
        Thread runner;
        Task(Thread runner){
            this.runner = runner;
        }

        @Override
        public void run() {
            try {
                System.out.println(String.format("[%s] 执行中..",Thread.currentThread().getName()));
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            result = Integer.valueOf(9987);

            //唤醒main线程
            synchronized (this){
                LockSupport.unpark(runner);
            }
            System.out.println(String.format("[%s] 执行完毕..",Thread.currentThread().getName()));
        }
    }

    public static Object submit(){
        CallbackTest callbackTest = new CallbackTest();
        Task task = callbackTest.new Task(Thread.currentThread());
        final String threadName = "T-1";
        Thread thread = new Thread(task,threadName);

        thread.start();

        while (true){
            if(task.getResult()==null){
                System.out.println(String.format("[%s] 等待执行..",Thread.currentThread().getName()));
                LockSupport.park(); //main线程阻塞
            }

            if(task.getResult()!=null){
                return task.getResult();
            }
        }
    }

    public static void main(String[] args) {

        CallbackTest.submit();

    }
}
