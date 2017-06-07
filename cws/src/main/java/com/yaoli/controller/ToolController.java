package com.yaoli.controller;

import com.yaoli.beans.Area;
import com.yaoli.beans.DetectionData;
import com.yaoli.beans.Sewage;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IDetectionDataService;
import com.yaoli.service.ISewageService;
import com.yaoli.service.IStatisticDayService;
import com.yaoli.vo.WaterCorrectingVo;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by will on 2017/2/11.
 */
@Controller
@RequestMapping("/tools")
public class ToolController {
    private static Logger logger = Logger.getLogger(ToolController.class);

    @Resource
    private ISewageService iSewageService;

    @Resource
    private IStatisticDayService iStatisticDayService;

    @Resource
    private IDetectionDataService iDetectionDataService;

    @Resource
    public IAreaService isAreaService;


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
    @RequestMapping("/gotoCorrectwater.do")
    public String gotoAddNewUsers(HttpServletRequest request, HttpServletResponse response, Model model){
        List<Area> allAreas = isAreaService.getAllAreas();
        model.addAttribute("allAreas", allAreas);
        return "/tools/correctwater";
    }

    @RequestMapping("/correctwater.do")
    protected void correctwater(@RequestBody WaterCorrectingVo vo, HttpServletRequest request, HttpServletResponse response, Model model) throws InterruptedException, ParseException {

        List<Sewage> sewages = null;
            //查询所有
        if(vo.getAreaid() == -1 && vo.getSewageid() == null){
            sewages = iSewageService.getAllSewages();
            //查询区县
        }else if(vo.getAreaid() != null && vo.getSewageid() == null){
            sewages = iSewageService.getSewagesByAreaId(vo.getAreaid());
            //查询单个站点
        }else if(vo.getAreaid() != null && vo.getSewageid() != null){
            Sewage sewage = iSewageService.selectByPrimaryKey(vo.getSewageid());
            sewages = new ArrayList<Sewage>();
            sewages.add(sewage);
        }

        //设置时间

        //开始时间
        DateTime begintime = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(vo.getBegintime());

        //结束时间
        DateTime endtime = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(vo.getEndtime());

        int diff = Days.daysBetween(begintime,endtime).getDays();

        if(diff > 61 || diff < 0){
            logger.info("时间超过两个月");
        }

        while(diff >= 0){

            DateTime preDate = begintime;

            try {
                for(Sewage sewage : sewages){
                    double water = 0.0;

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
                        statisticDayService.put("testingtime",begintime.toString("yyyy-MM-dd"));
                        statisticDayService.put("water",String.valueOf(water));
                        iStatisticDayService.updateWaterBySewageidAndDatetime(statisticDayService);
                        logger.info("站点:"+sewage.getName()+"id:"+sewage.getSewageid()+";日期:"+begintime.toString("yyyy-MM-dd")+";水量:"+water);
                    }else{
                        // 对于换了流量计的站点，如果按正常规则计算小于0，则不对该天的水量，校正。
                        // 则什么也不做
                        logger.info("站点名称:"+sewage.getName()+";日期:"+begintime.toString("yyyy-MM-dd")+";水量小于0，更换了流量计");
                    }
                }
            } catch (Exception e){
                logger.error("error",e);
            }



            begintime = begintime.plusDays(1);

            diff = Days.daysBetween(begintime,endtime).getDays();
        }

        logger.info("水量校正结束");
    }
}
