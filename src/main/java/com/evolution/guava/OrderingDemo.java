package com.evolution.guava;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.List;

/**
 * @description: 比较器
 * @author: liuzijian
 * @date: 2018-04-12 16:39
 */
public class OrderingDemo {
    public static List<Integer> use(List<Integer> list) {
        return Ordering.natural().reverse().nullsFirst().sortedCopy(list);
    }

    public static void main(String[] args) {
        List<Integer> source = Lists.newLinkedList(ImmutableList.of(1, 3, 6, 8, -1, 28, -6, -83, 32));
        source.add(3, null);
        List<Integer> resList = use(source);
        System.out.println(resList);

        int min = Ordering.natural().min(1, 5, 7, 29, 13);
        System.out.println(min);

        //source的null过滤
        int min2 = Ordering.natural().min(Collections2.filter(source, Predicates.notNull()));
        System.out.println(min2);
    }
}
