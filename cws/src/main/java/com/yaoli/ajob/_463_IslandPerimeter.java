package com.yaoli.ajob;

/**
 * Created by will on 2017/2/8.
 */
public class _463_IslandPerimeter {


    /**
     * 此题最关键的是公式
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        int isnums = 0;
        int bian = 0;
        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; j < grid[i].length ; j++){
                if(grid[i][j] == 1){
                    isnums ++;
                }
                if(j < grid.length - 1){
                    //横着看  | | |
                    if(grid[i][j] == 1 && grid[i][j+1] ==1){
                        bian++;
                    }
                }

                if(j < grid.length - 1){
                    //竖着看  | | |
                    if(grid[i][j] == 1 && grid[i + 1][j] ==1){
                        bian++;
                    }
                }
            }


        }

        return isnums*4 - bian*2;
    }

    public int islandPerimeter1(int[][] grid) {
        int isnums = 0;
        int bian = 0;
        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; j < grid[i].length ; j++){
                if(grid[i][j] == 1){
                    isnums ++;
                }
            }
        }

        for(int i = 0 ; i < grid.length ; i++){
            for(int j = 0 ; j < grid[i].length - 1 ; j++){
                //横着看  | | |
                if(grid[i][j] == 1 && grid[i][j+1] ==1){
                    bian++;
                }
            }
        }

        for(int i = 0 ; i < grid.length - 1; i++){
            for(int j = 0 ; j < grid[i].length; j++){
                //竖着看  | | |
                if(grid[i][j] == 1 && grid[i + 1][j] ==1){
                    bian++;
                }
            }
        }

        return isnums*4 - bian*2;
    }
}
