package com.yaoli.message;

import com.yaoli.beans.Message;
import com.yaoli.beans.SerialBean;
import com.yaoli.message.MessageUtil;
import com.yaoli.message.SerialUtilBean;
import com.yaoli.util.InfoUtil;

/**
 * Created by Administrator on 2017/4/7.
 */
public class _000_TestMessage {
    public static void main(String[] args) throws Exception {
//        SerialBean serialBean = new SerialBean(1);
//        //初始化
//        serialBean.initialize();
//
//        InfoUtil.sendMessage(serialBean, "15150662709","恭喜发财");
//
//        serialBean.closePort();

        SerialUtilBean bean = new SerialUtilBean();
        bean.initialize();
        //MessageUtil.sendMessage(bean, "15306174917","江都 出现的了 断电断线 下线");
        MessageUtil.resovleMessage(bean);
        bean.closePort();

//        String a = "科创通信";
//        String cod = SerialUtilBean.gbEncoding(a).replace("\\u","");
//
//        byte[] ss = cod.getBytes();
//
//        System.out.println(cod);
    }

}
