package com.evolution.leetcode.str;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * 给定一个字符串 s 和一些长度相同的单词 words。在 s 中找出可以恰好串联 words 中所有单词的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1:
 * 输入:
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出: [0,9]
 * 解释: 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 * 示例 2:
 * 输入:
 *   s = "wordgoodstudentgoodword",
 *   words = ["word","student"]
 * 输出: []
 *
 * @author: liuzijian
 * @create: 2019-01-01 13:34
 **/
public class LinkedStrIndexes {
    public List<Integer> findSubstring(String s, String[] words) {
        if(words==null || words.length==0){
            return Collections.EMPTY_LIST;
        }

        if(s==null || "".equals(s)){
            return Collections.EMPTY_LIST;
        }

        if(words[0]==null || "".equals(words[0])){
            return Collections.EMPTY_LIST;
        }

        int strLen = words[0].length();
        int wordsLen = words.length;

        if(s.length()<wordsLen*strLen){
            return Collections.EMPTY_LIST;
        }

        List<String> arrList = new ArrayList<>(wordsLen);
        for(String str:words){
            arrList.add(str);
            if(str.length()!=strLen){
                return Collections.EMPTY_LIST;
            }
        }

        List<Integer> res = new LinkedList<>();

        for(int i=0,len=s.length()-wordsLen*strLen+1;i<len;i++){
            int k = i;
            List<String> tempList = new LinkedList<>(arrList);

            while (true){
                String temp = s.substring(k,strLen+k);
                int index = 0;
                int j = k;
                for(String str:tempList){
                    if(temp.equals(str)){
                        k+=strLen;
                        break;
                    }
                    index++;
                }
                if(k==j){
                    break;
                }

                if(k>j){
                    tempList.remove(index);
                }

                if(tempList.size()==0){
                    break;
                }
            }

            if(tempList.size()==0){
                res.add(i);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LinkedStrIndexes si = new LinkedStrIndexes();
        List<Integer> resList = si.findSubstring("wordgoodgoodgoodbestword",new String[]{"word","good","best","good"});
        System.out.println(resList);
    }
}
