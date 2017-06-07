package com.yaoli.component;

import com.yaoli.beans.Sewage;
import com.yaoli.beans.WithoutElectricDataAbnormal;
import com.yaoli.service.ISewageService;
import com.yaoli.service.IWithoutElectricDataAbnormalService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

@Component
public class CheckWithoutElectricityEveryday {
	private static Logger logger = Logger.getLogger(CheckWithoutElectricityEveryday.class);
	
	@Resource
	private ISewageService iSewageService;

	@Resource
	private IWithoutElectricDataAbnormalService iWithoutElectricDataAbnormalService;
	
	protected void executeInternal() throws InterruptedException, ParseException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", null);
		List<Sewage> sewages = iSewageService.getWithoutElectric(map);

		DateTime dateTime = new DateTime();
		int year = dateTime.getYear();
		int month = dateTime.getMonthOfYear();
		int day = dateTime.getDayOfMonth();
		String dateString = year+"-"+month+"-"+day+" 09:00:00";
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime date = DateTime.parse(dateString, format);


		WithoutElectricDataAbnormal withoutElectricDataAbnormal = null;
		for(Sewage sewage : sewages){
			withoutElectricDataAbnormal = new WithoutElectricDataAbnormal();
			withoutElectricDataAbnormal.setSewageid(sewage.getSewageid());
			withoutElectricDataAbnormal.setIsrepaired((byte)0);
			withoutElectricDataAbnormal.setTestingtime(date.toDate());
			withoutElectricDataAbnormal.setLasttestingtime(date.toDate());
			iWithoutElectricDataAbnormalService.insertSelective(withoutElectricDataAbnormal);
		}
	}

}
