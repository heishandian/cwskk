package com.yaoli.config;

import com.yaoli.config.siteFunction.ImplDetect_1_to_5;
import com.yaoli.config.siteFunction.ImplEquip_1_to_21;

/**
 * Created by will on 2017/1/13.
 */
public class SiteHanJiang extends SewageSite implements ImplDetect_1_to_5,ImplEquip_1_to_21{
    @Override
    public String toString() {
        return "HJ";
    }
}
