package com.yaoli.config;

import com.yaoli.config.siteFunction.ImplDetect_1_to_5;
import com.yaoli.config.siteFunction.ImplEquip_1_to_5;

/**
 * Created by will on 2017/2/14.
 *
 * 锡山站点
 */
public class SiteXiShan extends SewageSite implements ImplEquip_1_to_5,ImplDetect_1_to_5 {
    @Override
    public String toString() {
        return "XS";
    }
}
