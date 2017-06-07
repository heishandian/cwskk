package com.yaoli.config;

import com.yaoli.config.siteFunction.ImplDetect_1_to_5_10_to_15;
import com.yaoli.config.siteFunction.ImplEquip_1_to_5;

/**
 * Created by will on 2017/1/6.
 */
public class SiteMianYuan extends SewageSite implements ImplEquip_1_to_5,ImplDetect_1_to_5_10_to_15 {
    @Override
    public String toString() {
        return "MY";
    }
}
