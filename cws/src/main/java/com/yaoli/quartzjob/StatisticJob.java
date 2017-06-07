package com.yaoli.quartzjob;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.yaoli.beans.Sewage;
import com.yaoli.service.ISewageService;
import com.yaoli.service.IStatisticService;

/**
 * 该类废弃，相关的功能移植到 insert_detection_data_abnormal 存储过程中
 * 详见 insert_detection_data_abnormal 模块三
 * @author will
 *
 */
@Component
@Deprecated
public class StatisticJob{
	private static Logger logger = Logger.getLogger(StatisticJob.class);
	
	@Resource
	private IStatisticService iStatisticService;
	
	@Resource
	private ISewageService iSewageService;
	
	/**
	 * 每天执行4次。3点 9点 15点 21点执行
	 * 
	 * 定义规则：如果在某个时间段中没有数据 则在数据库中不保存数据
	 * 比如：2点到3点 没有数据，则在数据库Statistic中没有这个时间段的数据
	 * 数据库时间段范围 0-23
	 * 
	 * 每小时统计一次
	 */
    protected void executeInternal(){
    	//获取所有的站点
    	List<Sewage> sewages = iSewageService.getAllSewages();
    	
    	//遍历站点 获取id，
    	for (Sewage sewage : sewages) {
			int sewageid = sewage.getSewageid();
			
			//先根据站点id 判断昨天该是否有数据
			int countYesterday = iSewageService.getSewageHasRecordYesterday(sewageid);
			//没有数据，再执行一次，因为可能是服务器没有开启
			if(countYesterday == 0){
				int count = iStatisticService.executeStatisticYesterday(sewageid);
				logger.info("站点："+sewage.getShortTitle()+"执行统计，插入statistic表"+count+"条数据");
			}else { //有数据则不执行任何操作，说明已经有效的执行统计任务
				logger.info("站点："+sewage.getShortTitle()+"执行统计，昨天已经存在统计数据");
			}
		}
    }
}
