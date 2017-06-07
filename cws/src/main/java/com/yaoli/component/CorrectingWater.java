package com.yaoli.component;

import com.yaoli.beans.DetectionData;
import com.yaoli.beans.Sewage;
import com.yaoli.service.IDetectionDataService;
import com.yaoli.service.ISewageService;
import com.yaoli.service.IStatisticDayService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by will on 2016/10/22.
 * 用于校正每个站点的水量
 * "水量数据校正功能：
 1.当天凌晨一点自动校正所有的站点昨天的水量。
 水量规则
 1.正常规则。水量定义为当天detection9的最后一条数据 减去 昨天detection9最后一条数据
 2.对于断电断线站点，比如，3号校正2号水量。1号有水量，2号早上有水量，下午断电断线，3号全天断点断线。则2号的数量为按正常规则计算。
 2.对于断电断线站点，比如，4号校正3号水量。1号有水量，2号早上有水量，下午断电断线，3号全天断点断线。则3号的水量为0。
 3.对于断电断线站点，比如，5号校正4号水量。1号有水量，2号早上有水量，下午断电断线，3号全天断点断线，4号有水量。则4号的水量是4号的最后一条数据 减去 2号的最后一条数据。
 4.对于换了流量计的站点，如果按正常规则计算小于0，则不对该天的水量，校正。
 NO123
 流量校准时间由凌晨一点改为早上九点
 */
@Component
public class CorrectingWater {
    private static Logger logger = Logger.getLogger(CorrectingWater.class);

    @Resource
    private ISewageService iSewageService;

    @Resource
    private IStatisticDayService iStatisticDayService;

    @Resource
    private IDetectionDataService iDetectionDataService;


    //测试方法 针对12号站点
    //statistic_day 里面 当天、昨天、前天、大前天初始化

    //测试正常情况   测试通过
    //detection_data 里面 昨天和前天的数据 有12号站点的数据

    //测试有天断线
    //detection_data 里面 昨天和大前天数据 有12号站点的数据

    //测试只有一天数据 ，昨天的最后一条数据 的总水量。测试通过
    //detection_data 里面 昨天的数据 有12号站点的数据

    //测试昨天断电断线 测试通过
    //detection_data 里面 没有昨天的数据
    //水量设置为0

    //测试更换水量器
    //detection_data 里面 昨天的比前天的数据小 有12号站点的数据

    protected void executeInternal() throws InterruptedException, ParseException {
        List<Sewage> sewages = iSewageService.getAllSewages();

        try {
            for(Sewage sewage : sewages){
                double water = 0.0;

                //名字起得不行 其实是当天时间
                DateTime preDate = new DateTime();

                //封装 当天时间参数
                Map<String,String> dateMap = new HashMap<String, String>();
                dateMap.put("sewageid",sewage.getSewageid().toString());
                dateMap.put("testingtime",preDate.toString("yyyy-MM-dd"));

                //获取当天detection9
                DetectionData dateDetectionData = iDetectionDataService.getDetectionDataBySewageidAndDatetime(dateMap);

                //当天断电断线，则水量校正为0
                if(dateDetectionData == null){
                    water = 0.0;
                }else if(dateDetectionData !=null){
                    //不为0，获取当天之前的最后一条数据
                    DetectionData preDetectionData = iDetectionDataService.getMaxPreDetectionDataBySewageidAndDatetime(dateMap);

                    if(preDetectionData == null){
                        water = dateDetectionData.getDetection9();
                    }else{
                        water = dateDetectionData.getDetection9() - preDetectionData.getDetection9();
                    }
                }


                if(water >= 0){//检查是否换流量计,大于0 校正
                    Map<String,String> statisticDayService = new HashMap<String, String>();

                    statisticDayService.put("sewageid",sewage.getSewageid().toString());
                    statisticDayService.put("testingtime",preDate.toString("yyyy-MM-dd"));
                    statisticDayService.put("water",String.valueOf(water));
                    iStatisticDayService.updateWaterBySewageidAndDatetime(statisticDayService);
                    logger.info("站点名称:"+sewage.getName()+";日期:"+preDate.toString("yyyy-MM-dd")+";水量:"+water);
                }else{
                    // 对于换了流量计的站点，如果按正常规则计算小于0，则不对该天的水量，校正。
                    // 则什么也不做
                }
            }
        } catch (Exception e){
            logger.error("error",e);
        }
    }
}
