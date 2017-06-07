package com.yaoli.ajob;

/**
 * Created by will on 2017/2/7.
 */
public class _283_MoveZeroes {

    public static void main(String[] args) {
        int a []= {1,2};
        moveZeroes(a);
    }

    public static void moveZeroes(int[] nums) {
        int zerp = 0;
        int nump = 0;

        while(zerp < nums.length  && nump < nums.length ){

            for(int i = zerp; i < nums.length  ; i++){
                if(nums[i] == 0){
                    zerp = i;
                    break;
                }
            }

            for(int i = nump ; i < nums.length ; i++){
                if(nums[i] != 0){
                    nump = i;
                    break;
                }
            }

            //如果 两个指针都没有变，那么就说名没有0
            if(nump == 0 && zerp == 0){
                break;
            }

            //必须 zerp 小于 nump
            if(zerp < nump){
                int temp = nums[zerp];
                nums[zerp] = nums[nump];
                nums[nump] = temp;
                zerp ++;
                nump ++;
            }else{
                //只要数字指针 ++；
                nump ++;
            }



        }

    }
}
