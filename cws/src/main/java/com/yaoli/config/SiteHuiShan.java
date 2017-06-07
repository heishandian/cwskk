package com.yaoli.config;

import com.yaoli.config.siteFunction.ImplDetect_1_to_5;
import com.yaoli.config.siteFunction.ImplEquip_1_to_5;

/**
 * Created by will on 2017/1/6.
 */
public class SiteHuiShan extends SewageSite
        implements ImplEquip_1_to_5,ImplDetect_1_to_5 {
    @Override
    public String toString() {
        return "HS";
    }
}
