package com.evolution.leetcode.str;

import org.assertj.core.util.Lists;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 140-单词拆分II，难度升级    TODO
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
 * 返回所有这些可能的句子。
 *
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 *
 * @program: evolution
 * @Author: Lzj
 * @Date: 2019/3/1 9:21
 */
public class WordsSplit2 {

    //"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    //["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
    public boolean canSplit(String s, Map<String,String> dictMap) {
        int n = s.length();
        boolean[] pack = new boolean[n+1];
        pack[0] = true;
        for(int i=1;i<=n;i++){
            for(int k=0;k<i;k++){
                if(pack[k] && dictMap.get(s.substring(k,i))!=null){
                    pack[i] = true;
                    break;
                }
            }
        }
        return pack[n];
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String,String> dictMap = wordDict.stream().collect(Collectors.toMap(x->{return x;}, v->{return "";}));
        boolean canSplit = canSplit(s,dictMap);
        if(!canSplit){
            return Collections.emptyList();
        }

        int len = s.length();
        LinkedList<String>[] m = new LinkedList[len+1];

        for(int i=1;i<=len;i++){
            LinkedList<String> iList = null;
            for(int k=0;k<i;k++){
                String temp = s.substring(k,i);
                if(dictMap.get(temp)!=null){
                    if(iList==null){
                        iList = new LinkedList<>();
                    }
                    if(k==0){
                        iList.add(temp);
                    }else if(m[k]!=null && m[k].size()>0){
                        LinkedList<String> beforeList = m[k];
                        for(String before:beforeList){
                            iList.add(before+" "+temp);
                        }
                    }
                }
            }
            m[i] = iList;
        }
        return m[len];
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict = Lists.newArrayList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        WordsSplit2 ws2 = new WordsSplit2();
        List<String> resList = ws2.wordBreak(s,wordDict);
        System.out.println(resList);
    }
}
