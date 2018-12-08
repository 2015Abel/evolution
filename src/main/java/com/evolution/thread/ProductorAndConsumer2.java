package com.evolution.thread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: ProductorAndConsumer
 * @author: liuzijian
 * @date: 2018-12-06 20:11
 */
public class ProductorAndConsumer2 {

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();

    private final int limit = 20;

    private LinkedList<Integer> repo = new LinkedList<>();

    public void create(Integer bullet) throws InterruptedException {
        Thread.yield();

        lock.lock();
        if (repo.size()>=limit){
            condition1.await();
        }

        repo.add(bullet);
        condition2.signal();
        System.out.println(Thread.currentThread().getName()+" 压入子弹-"+bullet+"，弹膛中共"+repo.size()+"颗子弹");
        lock.unlock();
    }

    public Integer shoot() throws InterruptedException {
        Thread.yield();

        lock.lock();
        if (repo.size()==0){
            condition2.await();
        }

        Integer bullet = repo.remove();
        System.out.println(Thread.currentThread().getName()+" 射出子弹-"+bullet+"，弹膛中共"+repo.size()+"颗子弹");
        condition1.signal();
        lock.unlock();
        return bullet;
    }
}
