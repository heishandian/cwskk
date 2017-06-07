package com.yaoli.ajob;

/**
 * Created by will on 2017/2/12.
 */
public class _459_RepeatedSubstringPattern {
    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("aba"));//bb,a
    }

    public static boolean repeatedSubstringPattern(String str) {

        //考察的是计算最大约数

        // 1/2 1/3 1/4 1/5 ... 1/(n/2)

        //bb aba

        if(str.length() == 1){
            return false;
        }
        int begin = str.length() / 2;

        boolean pass = true;

        while(begin >= 1){

            pass = true;

            if(str.length() % begin == 0){

                for(int i = 0 ; i < begin ; i++){

                    char temp = str.charAt(i);

                    for(int j = i + begin ; j < str.length(); j = j + begin ){

                        if(temp != str.charAt(j)){
                            //不相等
                            pass = false;
                            break;
                        }
                    }

                    if(pass == false){ //失败
                        break;
                    }
                }

                if(pass == false){ //失败寻找下一个 公约数
                    begin -- ;
                    continue;
                }

                pass = true;

                return pass;
            }else{
                begin --;
            }

        }

        return pass;
    }
}
