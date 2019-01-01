package com.evolution.leetcode.arr;

/**
 * @description:
 *
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1:
 * 给定 nums = [3,2,2,3], val = 3,
 *
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2:
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 *
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 *
 * 注意这五个元素可为任意顺序。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * @author: liuzijian
 * @create: 2018-12-31 20:18
 **/
public class RemoveEquals {
    public int removeElement(int[] nums, int val) {
        if(nums==null || nums.length==0){
            return 0;
        }

        int res = 0;
        boolean swap = false;
        for(int i=0,j=nums.length-1;i<=j;i++){
            if(nums[i]==val){
                while (i!=j){
                    if(nums[j]!=val){
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                        j--;
                        swap = true;
                        break;
                    }
                    j--;
                }
            }
            res  = i;
        }

        if(!swap && nums[res]==val){
            return res;
        }

        if(res+1<nums.length && nums[res]==val){
            return res;
        }
        return res+1;
    }

    public int removeElementOffical(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {3,3,3,2};
        RemoveEquals re = new RemoveEquals();
        int res = re.removeElement(nums,3);
        System.out.println(res);
    }
}
