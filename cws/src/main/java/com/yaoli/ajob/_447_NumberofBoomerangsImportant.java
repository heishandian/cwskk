package com.yaoli.ajob;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by will on 2017/3/4.
 */
public class _447_NumberofBoomerangsImportant {


    /**
     * 这道题目的思想是，从以第一个点开始，再次计算与其他点的距离
     *
     * 所以 这里 i = 0 ； j = 0
     *
     * 然后将计算出来的距离 保存在map中，
     *
     * 最后迭代map.values()的值
     *
     * 使用 val*（val-1）计算  例如 有四个点的位置 是 1 ，
     *
     * 1 1 1 1
     *
     * 这样 就有 12 对这样的点
     *
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {

        Map<Integer,Integer> map = new HashMap<Integer,Integer>();

        int res = 0;

        for(int i = 0; i < points.length ; i ++){
            for(int j = 0 ; j < points.length ; j++){
                int dis = calcDis(points[i],points[j]);

                if(!map.containsKey(dis)){
                    map.put(dis,1);
                }else{
                    map.put(dis,map.get(dis)+1);
                }
            }

            for(Integer val : map.values()){
                res = res + val*(val-1);
            }

            map.clear();
        }

        return res;
    }

    public int calcDis(int [] p1, int [] p2){
        int a = Math.abs(p1[0] - p2[0]);

        int b = Math.abs(p1[1] - p2[1]);

        return a*a + b*b;
    }

}
