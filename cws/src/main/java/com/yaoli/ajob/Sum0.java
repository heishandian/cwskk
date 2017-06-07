package com.yaoli.ajob;

import java.util.*;

/**
 * Created by will on 2017/1/4.
 * https://leetcode.com/problems/3sum/
 */
public class Sum0 {
    public static void main(String[] args) {
        threeSum(null);
    }


    public static List<List<Integer>> threeSum(int [] numsa){
        int nums[] =  {-9,14,-7,-8,9,1,-10,-8,13,12,6,9,3,-3,-15,-15,1,8,-7,-4,-6,8,2,-10,8,11,-15,3,0,-11,-1,-1,10,0,6,5,-14,3,12,-15,-7,-5,9,11,-1,1,3,-15,-5,11,-12,-4,-4,-2,-6,-10,-6,-6,0,2,-9,14,-14,-14,-9,-1,-2,-7,-12,-13,-15,-4,-3,1,14,3,-12,3,3,-10,-9,-1,-7,3,12,-6,0,13,4,-15,0,2,6,1,3,13,8,-13,13,11,11,13,14,-6};

        Arrays.sort(nums);

        Map<Integer,Integer> mapsNums = new HashMap<Integer, Integer>();
        Map<Integer,Integer> numsPosition = new HashMap<Integer, Integer>();

        for(int i = 0 ; i < nums.length ; i++){
            if (mapsNums.get(nums[i]) == null){
                mapsNums.put(nums[i],1);
                numsPosition.put(nums[i],i);
            }else{
                mapsNums.put(nums[i], mapsNums.get(nums[i]) + 1);
            }

        }

        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        List<Integer> list = null;

        boolean flag = true;
        while(flag==true){
            int firstPoint = 0;
            int lastPoint = nums.length - 1;

            int result = nums[firstPoint] + nums[lastPoint];

            int leftNum = 0 - result;

            //三个数全不相等
            if(nums[firstPoint] != nums[lastPoint] && nums[firstPoint] != leftNum && leftNum != nums[lastPoint]){
                if(mapsNums.containsKey(leftNum) &&
                        firstPoint < numsPosition.get(result) &&
                        numsPosition.get(result) < lastPoint){

                    list.add(nums[firstPoint]);
                    list.add(numsPosition.get(result));
                    list.add(nums[lastPoint]);

                }
            }


            firstPoint++;
            lastPoint--;

        }

        return lists;
    }

//    public static List<List<Integer>> threeSum(int [] numsa){
//        int nums[] =  {-9,14,-7,-8,9,1,-10,-8,13,12,6,9,3,-3,-15,-15,1,8,-7,-4,-6,8,2,-10,8,11,-15,3,0,-11,-1,-1,10,0,6,5,-14,3,12,-15,-7,-5,9,11,-1,1,3,-15,-5,11,-12,-4,-4,-2,-6,-10,-6,-6,0,2,-9,14,-14,-14,-9,-1,-2,-7,-12,-13,-15,-4,-3,1,14,3,-12,3,3,-10,-9,-1,-7,3,12,-6,0,13,4,-15,0,2,6,1,3,13,8,-13,13,11,11,13,14,-6};
//
//        Map<String,String> result = new HashMap<String, String>();
//
//        Arrays.sort(nums);
//
//        Map<Integer,Integer> maps = new HashMap<Integer, Integer>();
//        for(int i = 0 ; i < nums.length ; i++){
//            if (maps.get(nums[i]) == null){
//                maps.put(nums[i],1);
//            }else{
//                maps.put(nums[i], maps.get(nums[i]) + 1);
//            }
//        }
//
//        List<List<Integer>> lists = new ArrayList<List<Integer>>();
//        List<Integer> list = null;
//
//        for(int i = 0; i < nums.length ; i ++){
//
//
//            for(int j = i + 1; j < nums.length; j++){
//                StringBuilder builder = new StringBuilder();
//                list = new ArrayList<Integer>();
//
//                int tempNum = nums[i] + nums[j];
//
//                if(nums[i] == -4 && nums[j] ==2){
//                    System.out.println();
//                }
//                if(tempNum < 0){
//
//                    int leftNum = 0 - tempNum;
//
//                    int tempP = 0;
//
//
//                    for(int u = 0 ; u < nums.length; u++){
//                        if ( leftNum == nums[u]){
//                            tempP = u;
//                        }
//                    }
//
//                    if(maps.containsKey(leftNum) && tempP > j){
//                        if(leftNum == nums[i] || leftNum == nums[j]){
//                            if(maps.get(leftNum) >= 2){
//                                list.add(nums[i]);
//                                list.add(nums[j]);
//                                list.add(leftNum);
//                            }
//                        }else {
//                            list.add(nums[i]);
//                            list.add(nums[j]);
//                            list.add(leftNum);
//                        }
//
//                        builder.append(nums[i]);
//                        builder.append(",");
//                        builder.append(nums[j]);
//                        builder.append(",");
//                        builder.append(leftNum);
//                    }
//                }else if(tempNum == 0){
//                    if(maps.get(0) != null && maps.get(0) >= 3){
//                        list.add(0);
//                        list.add(0);
//                        list.add(0);
//
//                        builder.append(0);
//                        builder.append(",");
//                        builder.append(0);
//                        builder.append(",");
//                        builder.append(0);
//                    }
//                }else if(tempNum > 0){
//                    break;
//                }
//
//                if(list.size() != 0){
//
//                    if(!result.containsKey(builder.toString())){
//                        result.put(builder.toString(),null);
//                        lists.add(list);
//                    }
//
//                }
//            }
//
//        }
//
//        return lists;
//    }

}
