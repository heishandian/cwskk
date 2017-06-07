package com.yaoli.message;

import com.yaoli.beans.SerialBean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/4/7.
 */
public class MessageUtil {
    private static final Logger logger  = Logger.getLogger(SerialBean.class.getName());

    //将汉字转成10进制
    public static List<Integer> toUnicode(String s){
        String as[] = new String[s.length()];
        String [] cover = {"","000","00","0"};
        List<Integer> datas = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++){
            as[i] = Integer.toHexString(s.charAt(i) & 0xffff);
            if (as[i].length() != 4){
                as[i] = cover[as[i].length()]+as[i];
            }
            datas.add(Integer.parseInt(as[i].substring(0, 2),16));
            datas.add(Integer.parseInt(as[i].substring(2, 4),16));
        }

        String a = "";
        String b = "";
        for(int i = 0 ; i < datas.size() ; i++){
            a = a + datas.get(i);
            b = b + datas.get(i) + " ";
        }
        return datas;
    }

    //发送短信
    public static void sendMessage(SerialUtilBean serialUtilBean,String tel,String msg) throws Exception{
        synchronized (MessageUtil.class) {
            String result;
            //String cmd;
            String cmd = "AT\r";
            serialUtilBean.writePort(cmd);
            //result = serialUtilBean.readPort();
            result = serialUtilBean.readPort2();
            logger.info(result);

            cmd = "AT+ZMSGL=6,4\r";
            serialUtilBean.writePort(cmd);
//            result = serialUtilBean.readPort();
            result = serialUtilBean.readPort2();
            logger.info(result);

            cmd = "AT+CMGS="+tel+"\r";
            serialUtilBean.writePort(cmd);
//            result = serialUtilBean.readPort();
            result = serialUtilBean.readPort2();
            logger.info(result);

            List<Integer> list = MessageUtil.toUnicode(msg);
            serialUtilBean.writeH(list);
            result = serialUtilBean.readPort3();
            logger.info(result);

        }
    }


    /**
     * 解析短信
     * @param serialUtilBean
     * @throws Exception
     */
    public static List<SerialMessageBean> resovleMessage(SerialUtilBean serialUtilBean) throws Exception{
        synchronized (MessageUtil.class) {
            String result;
            String cmd;

            cmd = "AT\r";
            serialUtilBean.writePort(cmd);
            result = serialUtilBean.readPort();

            List<SerialMessageBean> beans = new ArrayList<SerialMessageBean>();
            SerialMessageBean sericalMessageBean;

            List<Integer> deleteNos = new ArrayList<Integer>();
            //查询短信
            for (int i = 0; i < 40; i++) {
                cmd = "AT+CMGR="+i+"\r";
                serialUtilBean.writePort(cmd);
                Thread.sleep(200L);
                sericalMessageBean = serialUtilBean.resolveMessage();
                //Thread.sleep(100L);
                if(sericalMessageBean != null){
                    beans.add(sericalMessageBean);
                    System.out.println("NO:"+i);
                    deleteNos.add(i);
                }
                //logger.info(result.replace("\\r"," ").replace("\\n"," "));
            }

            //删除短信
            for (int i = 0; i < deleteNos.size(); i++) {
                cmd = "AT+CMGD="+(deleteNos.get(i))+"\r";
                serialUtilBean.writePort(cmd);
                Thread.sleep(300L);
                result = serialUtilBean.readPort2();
                //Thread.sleep(100L);
                logger.info(result);
            }
            return beans;
        }
    }

}
