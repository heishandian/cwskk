package com.yaoli.config;

import com.yaoli.config.siteFunction.ImplDetect_1_to_5;
import com.yaoli.config.siteFunction.ImplEquip_6_to_21;

/**
 * Created by will on 2017/1/6.
 */
public class SiteJiangDu extends SewageSite implements ImplEquip_6_to_21,ImplDetect_1_to_5 {
    @Override
    public String toString() {
        return "JD";
    }
}
