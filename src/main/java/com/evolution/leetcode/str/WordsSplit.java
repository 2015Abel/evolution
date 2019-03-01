package com.evolution.leetcode.str;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * @program: evolution
 * @Author: Lzj
 * @Date: 2019/3/1 9:21
 */
public class WordsSplit {

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] pack = new boolean[n+1];
        pack[0] = true;
        for(int i=1;i<=n;i++){
            for(int k=0;k<i;k++){
                if(pack[k] && wordDict.contains(s.substring(k,i))){
                    pack[i] = true;
                    break;
                }
            }
        }
        return pack[n];
    }

    public static void main(String[] args) {
        WordsSplit wordsSplit = new WordsSplit();
        String words = "dog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("dog");
        wordDict.add("d");
        boolean res = wordsSplit.wordBreak(words,wordDict);
        System.out.println(res);
    }
}
