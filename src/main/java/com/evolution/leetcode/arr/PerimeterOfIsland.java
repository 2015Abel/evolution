package com.evolution.leetcode.arr;

/**
 * @Description: 463. 岛屿的周长
 *
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 *
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。
 * 整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
 * 格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 * 示例 :
 *
 * 输入:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * 输出: 16
 *
 * 解释: 它的周长是下面图片中的 16 个带 "*"号的边：
 *  —— —— —— ——
 * |  |* |  |  |
 *  —— —— —— ——
 * |* |* |* |  |
 *  —— —— —— ——
 * |  |* |  |  |
 *  —— —— —— ——
 * |* |* |  |  |
 *  —— —— —— ——
 *
 * @program: evolution
 * @Author: Lzj
 * @Date: 2019/3/5 16:13
 */
public class PerimeterOfIsland {
    public int islandPerimeter(int[][] grid) {
        int miniIslandNum = 0;
        int height = grid.length;
        int len = grid[0].length;
        final int islandCode = 1;
        int repeat = 0;
        for(int i=0;i<height;i++){
            int k=0;
            while (k<len){
                //grid[i][k]
                if(grid[i][k]==islandCode){
                    miniIslandNum++;
                    if(i>0 && grid[i-1][k]==islandCode){
                        repeat++;
                    }
                    if(i<height-1 && grid[i+1][k]==islandCode){
                        repeat++;
                    }
                    if(k>0 && grid[i][k-1]==islandCode){
                        repeat++;
                    }
                    if(k<len-1 && grid[i][k+1]==islandCode){
                        repeat++;
                    }
                }

                k++;
            }
        }
        return miniIslandNum*4 - repeat;
    }

    public static void main(String[] args) {
        PerimeterOfIsland bean = new PerimeterOfIsland();
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        int res = bean.islandPerimeter(grid);
        System.out.println(res);
    }
}
