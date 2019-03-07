package com.evolution.leetcode.arr;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Description: 841. 钥匙和房间
 *
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 *
 * 你可以自由地在房间之间来回走动。
 * 如果能进入每个房间返回 true，否则返回 false。
 *
 * 示例 1：
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 *
 * 示例 2：
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 *
 * 提示：
 * 1. 1 <= rooms.length <= 1000
 * 2. 0 <= rooms[i].length <= 1000
 * 3. 所有房间中的钥匙数量总计不超过 3000。
 *
 * @program: evolution
 * @Author: Lzj
 * @Date: 2019/3/7 15:58
 */
public class KeyAndRoom {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms.size()<=1){
            return true;
        }

        Set<Integer> finishedRooms = new HashSet<>(rooms.size());
        finishedRooms.add(0);
        LinkedList<Integer> targetList = new LinkedList<>();

        int roomNum = rooms.size();

        boolean res = false;
        targetList.addAll(rooms.get(0));
        while (true){
            if(targetList.size()==0){
                break;
            }
            int temp = targetList.removeLast();
            if(finishedRooms.contains(temp)){
                continue;
            }else {
                finishedRooms.add(temp);
                if(finishedRooms.size() == roomNum){
                    res = true;
                    break;
                }

                List<Integer> keys = new ArrayList<>(rooms.get(temp));
                keys.removeAll(finishedRooms);
                targetList.addAll(keys);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        KeyAndRoom kr = new KeyAndRoom();
        List<List<Integer>> rooms = new LinkedList<>();
        rooms.add(ImmutableList.of(1,3));
        rooms.add(ImmutableList.of(3,0,1));
        rooms.add(ImmutableList.of(2));
        rooms.add(ImmutableList.of(0));
        boolean res = kr.canVisitAllRooms(rooms);
        System.out.println(res);
    }


}
