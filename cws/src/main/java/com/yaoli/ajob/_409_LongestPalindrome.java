package com.yaoli.ajob;

import java.util.Map;
import java.util.HashMap;
/**
 * Created by will on 2017/2/9.
 */
public class _409_LongestPalindrome {
    public static void main(String[] args) {
        String s = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        longestPalindrome(s);

    }

    public static int longestPalindrome(String s) {
        char [] ss = s.toCharArray();

        Map<Character,Integer> map = new HashMap<Character,Integer>();

        for(int i = 0 ;i < ss.length ; i ++){
            if(map.containsKey((Character)ss[i])){
                map.put(ss[i],map.get(ss[i])+1);
            }else{
                map.put(ss[i],1);
            }
        }

        int single = 0;

        int count = 0;

        boolean hasone = false;

        for(Character c : map.keySet()){
            if(map.get(c) % 2 == 1){
                count = map.get(c) - 1 + count;
                hasone = true;
            }else if(map.get(c) % 2 == 0){
                count = map.get(c) + count;
            }
        }

        if(hasone == true){
            return count+1;
        }else{
            return count;
        }

    }
}
