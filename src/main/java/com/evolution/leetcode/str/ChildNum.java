package com.evolution.leetcode.str;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 438. 找到字符串中所有字母异位词
 *
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 *
 * 示例 1:
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *
 * 示例 2:
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * @program: evolution
 * @Author: Lzj
 * @Date: 2019/3/8 14:13
 */
public class ChildNum {

    /**
     * 用一个length=26的数组，记录字符串（类似计数排序的方式），并进行比对；
     * 并使用滑块提升性能
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if(p.length()>s.length()){
            return Collections.emptyList();
        }

        int[] targetArr = new int[26];
        for(int c:p.toCharArray()){
            int count = targetArr[c-97];
            targetArr[c-97] = count+1;
        }

        List<Integer> resList = new LinkedList<>();

        int pLen = p.length();
        int sLen = s.length();
        char[] sArr = s.toCharArray();
        int[] curArr = new int[26];
        for(int i=0,len=sLen-pLen+1;i<len;i++){
            boolean notEquals = false;
            for(int k=i;k<pLen+i;k++){
                int idx = sArr[k]-97;
                int count = curArr[idx]+1;
                if(notEquals = (count>targetArr[idx])){
                    backToInit(curArr);
                    break;
                }else {
                    curArr[idx] = count;
                }
            }

            if(notEquals){
                continue;
            }

            resList.add(i);
            backToInit(curArr);

            // 滑块，提升性能
            // 假设 s=aba p=ab，当匹配上时(i=0)，判断下一个字符plen+i是否和当前匹配字符(i对应的)一致
            for(int k=i+pLen;k<sLen;k++){
                if(sArr[k]==sArr[i]){
                    i++;
                    resList.add(i);
                }else {
                    i++;
                    break;
                }
            }

        }
        return resList;
    }

    private int[] backToInit(int[] arr){
        for(int i=0,len=arr.length;i<len;i++){
            arr[i] = 0;
        }
        return arr;
    }

    public static void main(String[] args) {
        ChildNum childNum = new ChildNum();
        String s = "abacbabc";
        String p = "abc";
        List<Integer> list = childNum.findAnagrams(s,p);
        System.out.println(list);
    }
}
