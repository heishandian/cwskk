package com.yaoli.ajob;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by will on 2017/2/10.
 */
public class _202_HappyNumber {
    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }

    public static boolean isHappy(int n) {
        int power = 0;


        int newn = n;

        Set<Integer> sets = new HashSet<Integer>();

        while(newn != 0){

            while(true){
                power = power + ( newn % 10 ) * (newn % 10);
                newn = newn / 10;
                if(newn == 0){
                    break;
                }
            }

            if(sets.contains(power)){
                return false;
            }else{
                sets.add(power);
            }

            if(power == 1){
                return true;
            }

            if(power == n){
                return false;
            }

            newn = power;
            power = 0;
        }


        return false;
    }
}
