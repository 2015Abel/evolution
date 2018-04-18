package com.evolution.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: TODO 类描述
 * @author: liuzijian
 * @date: 2018-04-18 08:25
 */
public class FileOperationDemo {

    static class Task implements Runnable {

        String path = "/FileOperationDemo/";
        CountDownLatch countDownLatch;

        public Task(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                write(ai.incrementAndGet());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }

        public void write(int i) throws IOException {
            lock.lock();
            String data = LocalDateTime.now() + "\t" + i + "\n";
            System.out.print(data);

            File file = new File(path + "demo.log");
            FileUtils.writeStringToFile(file, data, "utf-8", true);
            lock.unlock();
        }
    }

    static ExecutorService es = Executors.newCachedThreadPool();

    static Lock lock = new ReentrantLock();

    static AtomicInteger ai = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

//        FileOperationDemo demo = new FileOperationDemo();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(new Task(countDownLatch)).start();
        }
        countDownLatch.await();
        es.shutdown();
    }
}
