package com.evolution.leetcode.str;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Description: 767. 重构字符串
 *
 * 题目描述
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 *
 * 示例 1:
 * 输入: S = "aab"
 * 输出: "aba"
 *
 * 示例 2:
 * 输入: S = "aaab"
 * 输出: ""
 *
 * 注意:
 * S 只包含小写字母并且长度在[1, 500]区间内。
 *
 * @program: evolution
 * @Author: Lzj
 * @Date: 2019/3/4 9:53
 */
public class StrRearrange {
    public String reorganizeString(String str) {
        int len = str.length();
        char[] chars = str.toCharArray();

        HashMap<Character,Integer> countMap = new HashMap<>();
        for(char c:chars){
            Integer count = countMap.get(c);
            if(count==null){
                count = 1;
            }else {
                count++;
            }
            countMap.put(c,count);
        }

        LinkedList<CharCounter> countList = new LinkedList<>();
        for (Map.Entry<Character,Integer> entry:countMap.entrySet()){
            countList.add(new CharCounter(entry.getKey(),entry.getValue()));
        }
        Collections.sort(countList);

        int maxCount = countList.get(0).count;
        if(maxCount-(len-maxCount)>1){
            return "";
        }

        StringBuilder bu = new StringBuilder();
        boolean flag = true;
        int charNum = len;
        while (charNum>0){
            CharCounter bean;
            if(flag){
                bean = countList.getFirst();
                bu.append(bean.c);
                bean.count--;
                if(bean.count==0){
                    countList.removeFirst();
                }
            }else {
                bean = countList.getLast();
                bu.append(bean.c);
                bean.count--;
                if(bean.count==0){
                    countList.removeLast();
                }
            }
            charNum--;
            flag = !flag;
        }

        // 头尾分取字符后，还要再次判断当前组成的字符串尾部是否有重复字符；
        // 如果有：尾部重复部分和头部等长的字串再次交替组合处理
        int i=1;
        char repeatChar = bu.charAt(len-i);
        while (repeatChar==bu.charAt(len-(i+1))){
            i++;
        }
        if(i==1){
            return bu.toString();
        }
        String finishedStr = bu.substring(i,len-i);
        String handleStr = bu.toString().replace(finishedStr,"");
        len = handleStr.length();
        StringBuilder handleBu = new StringBuilder(handleStr);
        StringBuilder handleRes = new StringBuilder();
        flag = true;
        while (len>0){
            char c;
            if(flag){
                c = handleBu.charAt(len-1);
                handleBu.deleteCharAt(len-1);
            }else {
                c = handleBu.charAt(0);
                handleBu.deleteCharAt(0);
            }
            flag = !flag;
            handleRes.append(c);
            len--;
        }
        return finishedStr+handleRes;
    }

    private class CharCounter implements Comparable<CharCounter>{
        char c;
        int count;

        public CharCounter(char c, int count) {
            this.c = c;
            this.count = count;
        }

        @Override
        public int compareTo(CharCounter o) {
            if(o.count>count){
                return 1;
            }else if(o.count==count){
                return 0;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        StrRearrange rearrange = new StrRearrange();
        String str = "tndsewnllhrtwsvxenkscbivijfqnysamckzoyfnapuotmdexzkkrpmppttficzerdndssuveompqkemtbwbodrhwsfpbmkafpwyedpcowruntvymxtyyejqtajkcjakghtdwmuygecjncxzcxezgecrxonnszmqmecgvqqkdagvaaucewelchsmebikscciegzoiamovdojrmmwgbxeygibxxltemfgpogjkhobmhwquizuwvhfaiavsxhiknysdghcawcrphaykyashchyomklvghkyabxatmrkmrfsppfhgrwywtlxebgzmevefcqquvhvgounldxkdzndwybxhtycmlybhaaqvodntsvfhwcuhvuccwcsxelafyzushjhfyklvghpfvknprfouevsxmcuhiiiewcluehpmzrjzffnrptwbuhnyahrbzqvirvmffbxvrmynfcnupnukayjghpusewdwrbkhvjnveuiionefmnfxao";
        String res = rearrange.reorganizeString(str);
        System.out.println(res);
    }
}


