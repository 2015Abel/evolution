package com.evolution.leetcode.str;

/**
 * @description:
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * @author: liuzijian
 * @create: 2018-12-31 23:18
 **/
public class IndexInTarget {
    public int strStr(String haystack, String needle) {
        if(needle==null || "".equals(needle)){
            return 0;
        }

        int hLen = haystack.length();
        int nLen = needle.length();
        if(nLen>hLen){
            return -1;
        }

        char[] hCharts = haystack.toCharArray();
        char[] nCharts = needle.toCharArray();
        for(int i=0,len=hLen-nLen+1;i<len;i++){
            int j = 0;
            int k = i;
            while (hCharts[k] == nCharts[j]){
                j++;
                k++;
                if(j==nLen){
                    return i;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        IndexInTarget it = new IndexInTarget();
        int res = it.strStr("mississippi","issip");
        System.out.println(res);
    }
}
