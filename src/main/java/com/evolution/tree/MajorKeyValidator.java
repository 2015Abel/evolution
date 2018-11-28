package com.evolution.tree;

import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: MajorKeyValidator
 *      模拟数据库表主键的唯一性验证
 *
 * @author: liuzijian
 * @date: 2018-11-27 13:52
 */
public class MajorKeyValidator {
    private static final Map<String,Set<Integer>> tables = new ConcurrentHashMap<>();

    private static final Lock lock = new ReentrantLock();

    public static boolean add(String tabName,Integer key){
        Set<Integer> idSet = tables.get(tabName);
        lock.lock();
        if(idSet==null){
            idSet = Sets.newConcurrentHashSet();
            tables.put(tabName,idSet);
        }
        lock.unlock();

        boolean res = idSet.contains(key);

        idSet.add(key);
        return res;
    }

    public static boolean del(String tabName,Integer key){
        Set<Integer> idSet = tables.get(tabName);
        if(idSet==null){
            return false;
        }

        return idSet.remove(key);
    }
}
