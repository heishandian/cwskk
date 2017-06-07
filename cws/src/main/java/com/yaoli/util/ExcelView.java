package com.yaoli.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaoli.config.SystemConfig;
import com.yaoli.config.siteFunction.ImplDetect_1_to_5;
import com.yaoli.config.siteFunction.ImplDetect_1_to_5_10_to_15;
import com.yaoli.config.siteFunction.ImplEquip_1_to_5;
import com.yaoli.config.siteFunction.ImplEquip_6_to_21;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.yaoli.vo.StatisticDayVO;

public class ExcelView extends AbstractExcelView {

	 protected void buildExcelDocument(Map<String, Object> model,  
	            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)  
	            throws Exception {
		//Map<String, String> map = CustomPropertyConfigurer.getProperties();
		//String systemNO = map.get("systemno");
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		//excelnameAndTitleName
		String excelnameAndTitleName = (String)model.get("areaname")+(String)model.get("reportname")+dateString;
		String encodeExcelnameAndTitleName = java.net.URLEncoder.encode(excelnameAndTitleName,"utf-8");
		
		//excel类型 1表示设备运行记录 2表示水质检测
		String exceltype = (String)model.get("exceltype");
		// 老系统
		//if(systemNO.equals("0")){
		 if(SystemConfig.site instanceof ImplEquip_1_to_5 && SystemConfig.site instanceof ImplDetect_1_to_5){
			@SuppressWarnings("unchecked")
			List<StatisticDayVO> statisticDayVOs = (List<StatisticDayVO>)model.get("statisticDayVOs");
			 
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=\""+encodeExcelnameAndTitleName+".xls\"");  
			HSSFSheet sheet = workbook.createSheet(excelnameAndTitleName);
			
			CellStyle cellStyle = workbook.createCellStyle();
	        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        
	        
			CellStyle titleStyle = workbook.createCellStyle();
			titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
			titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        HSSFFont font = workbook.createFont(); 
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 20);
            titleStyle.setFont(font);
	        

	        
	        
			//---设置“污水站点”标题
			HSSFRow row = sheet.createRow(1);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("污水站点");
			cell.setCellStyle(cellStyle);
			sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
			
			//标题 行位移
			int rowoffset = 1;
			int titlepoint = 1;
			//子标题行位移
			int rowoffset2 = 1;
			//填充数据的行控制 从第四行开始 前面已经有 0,1,2
			int controlRowIndex = 3;
			
			//不同的报表类型
			if(exceltype.equals("1")){
		        //设置 报表 标题：占9列
				HSSFRow titlerow = sheet.createRow(0);
				HSSFCell titlecell = titlerow.createCell(0);
				titlecell.setCellValue(excelnameAndTitleName);
				titlecell.setCellStyle(titleStyle);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
				
				//设置设备标题
				for (int i = 1; i <= 4; i++) {
					//
					sheet.addMergedRegion(new CellRangeAddress(1,1,rowoffset,rowoffset+1));
					rowoffset = rowoffset + 2;
					HSSFCell titleCell = row.createCell(titlepoint);
					titlepoint = titlepoint + 2;
					titleCell.setCellValue(SystemConfig.equips.get("equipment"+i+"name"));
					titleCell.setCellStyle(cellStyle);
				}
				HSSFRow row1 = sheet.createRow(2);
				//设置设备子标题：设备运行时间 和停止时间
				for (int i = 1; i <= 4; i++) {
					HSSFCell subtitleCell = row1.createCell(rowoffset2);
					subtitleCell.setCellValue("运行时间");
					subtitleCell.setCellStyle(cellStyle);
					rowoffset2 = rowoffset2 + 1;
					HSSFCell subtitleCel2 = row1.createCell(rowoffset2);
					subtitleCel2.setCellValue("停止时间");
					subtitleCel2.setCellStyle(cellStyle);
					rowoffset2 = rowoffset2 + 1;
				}
				
				
				//填充设备
				for (StatisticDayVO statisticDayVO : statisticDayVOs) {
					int controlColIndex = 0;
					
					HSSFRow dataRow = sheet.createRow(controlRowIndex);
					controlRowIndex = controlRowIndex + 1;
					Class<?> statisticDayVOClass = Class.forName("com.yaoli.vo.StatisticDayVO");
					//填充 设备名称
					Method getSewagenameMethod = statisticDayVOClass.getDeclaredMethod("getSewagename");
					String sewagename = getSewagenameMethod.invoke(statisticDayVO).toString();
					HSSFCell datanamecell = dataRow.createCell(controlColIndex);
					datanamecell.setCellValue(sewagename);
					datanamecell.setCellStyle(cellStyle);
					controlColIndex = controlColIndex + 1;
					
					//填充 设备运行、停止时间
					for (int i = 1; i <= 4; i++) {
						Method method1 = statisticDayVOClass.getDeclaredMethod("getEquip"+i+"normaltime");
						Object normaltimeObject = method1.invoke(statisticDayVO);
						Integer normaltime = (normaltimeObject == null ? 0:Integer.valueOf(normaltimeObject.toString()));
						
						Method method2 = statisticDayVOClass.getDeclaredMethod("getEquip"+i+"abnormaltime");
						Object abnormaltimeObject = method2.invoke(statisticDayVO);
						Integer abnormaltime = (normaltimeObject == null ? 0:Integer.valueOf(abnormaltimeObject.toString()));
						
						HSSFCell datacell = dataRow.createCell(controlColIndex);
						datacell.setCellValue(normaltime);
						datacell.setCellStyle(cellStyle);
						controlColIndex = controlColIndex + 1;
						
						HSSFCell datacel2 = dataRow.createCell(controlColIndex);
						datacel2.setCellValue(abnormaltime);
						datacel2.setCellStyle(cellStyle);
						controlColIndex = controlColIndex + 1;
					}
				}
				
				//填写制表人和制表时间
				HSSFRow dataRow = sheet.createRow(controlRowIndex);
				
				//制表人 7 8  制表时间9 10 11 12
				HSSFCell datacell1 = dataRow.createCell(4);
				datacell1.setCellValue("制表人");
				datacell1.setCellStyle(cellStyle);
				
				HSSFCell datacell2 = dataRow.createCell(5);
				datacell2.setCellValue((String)model.get("username"));
				datacell2.setCellStyle(cellStyle);
				
				//制表时间
				HSSFCell datacell3 = dataRow.createCell(6);
				datacell3.setCellValue("制表时间");
				datacell3.setCellStyle(cellStyle);
				
				//制表时间
				HSSFCell datacell4 = dataRow.createCell(7);
				datacell4.setCellValue((String)model.get("time"));
				datacell4.setCellStyle(cellStyle);
				sheet.addMergedRegion(new CellRangeAddress(controlRowIndex, controlRowIndex, 7, 8));
				controlRowIndex = controlRowIndex + 1;
			}else if(exceltype.equals("2")){
		        //设置 报表 标题：占9列
				HSSFRow titlerow = sheet.createRow(0);
				HSSFCell titlecell = titlerow.createCell(0);
				titlecell.setCellValue(excelnameAndTitleName);
				titlecell.setCellStyle(titleStyle);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
				
				//设置水质标题
				for (int i = 1; i <= 5; i++) {
					if(i != 4){
						//这里一定要注意
						sheet.addMergedRegion(new CellRangeAddress(1,1,rowoffset,rowoffset+2));
						rowoffset = rowoffset + 3;
						HSSFCell titleCell = row.createCell(titlepoint);
						titlepoint = titlepoint + 3;
						titleCell.setCellValue(SystemConfig.detections.get("detection"+i+"name"));
						titleCell.setCellStyle(cellStyle);
					}
				}
				HSSFRow row1 = sheet.createRow(2);
				//设置子标题：设备水质子标题 ：最大 最小 平均备
				for (int i = 1; i <= 4; i++) {
					//这里一定要注意
					HSSFCell subtitleCell = row1.createCell(rowoffset2);
					subtitleCell.setCellValue("最大");
					subtitleCell.setCellStyle(cellStyle);
					rowoffset2 = rowoffset2 + 1;
					HSSFCell subtitleCel2 = row1.createCell(rowoffset2);
					subtitleCel2.setCellValue("最小");
					subtitleCel2.setCellStyle(cellStyle);
					rowoffset2 = rowoffset2 + 1;
					HSSFCell subtitleCel3 = row1.createCell(rowoffset2);
					subtitleCel3.setCellValue("平均");
					subtitleCel3.setCellStyle(cellStyle);
					rowoffset2 = rowoffset2 + 1;
				}
				
				//填充水质
				for (StatisticDayVO statisticDayVO : statisticDayVOs) {
					int controlColIndex = 0;
					
					HSSFRow dataRow = sheet.createRow(controlRowIndex);
					controlRowIndex = controlRowIndex + 1;
					Class<?> statisticDayVOClass = Class.forName("com.yaoli.vo.StatisticDayVO");
					//填充 设备名称
					Method getSewagenameMethod = statisticDayVOClass.getDeclaredMethod("getSewagename");
					String sewagename = getSewagenameMethod.invoke(statisticDayVO).toString();
					HSSFCell datanamecell = dataRow.createCell(controlColIndex);
					datanamecell.setCellValue(sewagename);
					datanamecell.setCellStyle(cellStyle);
					controlColIndex = controlColIndex + 1;
					
					//填充 水质
					for (int i = 1; i <= 5; i++) {
						if(i != 4){
							Method method1 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"max");
							Object maxObject = method1.invoke(statisticDayVO);
							Double max = maxObject == null ? 0.00:Double.valueOf(String.format("%.2f", Double.valueOf(String.valueOf(maxObject))));

							Method method2 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"min");
							Object minObject = method2.invoke(statisticDayVO);
							Double min = minObject == null ? 0.00:Double.valueOf(String.format("%.2f", Double.valueOf(String.valueOf(minObject))));
							
							Method method3 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"avg");
							Object avgObject = method3.invoke(statisticDayVO);
							Double avg = avgObject == null ? 0.00:Double.valueOf(String.format("%.2f", Double.valueOf(String.valueOf(avgObject))));
							
							HSSFCell datacell = dataRow.createCell(controlColIndex);
							datacell.setCellValue(max);
							datacell.setCellStyle(cellStyle);
							controlColIndex = controlColIndex + 1;
							
							HSSFCell datacel2 = dataRow.createCell(controlColIndex);
							datacel2.setCellValue(min);
							datacel2.setCellStyle(cellStyle);
							controlColIndex = controlColIndex + 1;
							
							HSSFCell datacel3 = dataRow.createCell(controlColIndex);
							datacel3.setCellValue(avg);
							datacel3.setCellStyle(cellStyle);
							controlColIndex = controlColIndex + 1;
						}
					}
				}
				//填写制表人和制表时间
				HSSFRow dataRow = sheet.createRow(controlRowIndex);
				
				//制表人 7 8  制表时间9 10 11 12
				HSSFCell datacell1 = dataRow.createCell(7);
				datacell1.setCellValue("制表人");
				datacell1.setCellStyle(cellStyle);
				
				HSSFCell datacell2 = dataRow.createCell(8);
				datacell2.setCellValue((String)model.get("username"));
				datacell2.setCellStyle(cellStyle);
				
				//制表时间
				HSSFCell datacell3 = dataRow.createCell(9);
				datacell3.setCellValue("制表时间");
				datacell3.setCellStyle(cellStyle);
				
				//制表时间
				HSSFCell datacell4 = dataRow.createCell(10);
				datacell4.setCellValue((String)model.get("time"));
				datacell4.setCellStyle(cellStyle);
				sheet.addMergedRegion(new CellRangeAddress(controlRowIndex, controlRowIndex, 10, 12));
				controlRowIndex = controlRowIndex + 1;
			}
		}
		
		
		// 江都系统
		 if(SystemConfig.site instanceof ImplEquip_6_to_21 && SystemConfig.site instanceof  ImplDetect_1_to_5){
			@SuppressWarnings("unchecked")
			List<StatisticDayVO> statisticDayVOs = (List<StatisticDayVO>)model.get("statisticDayVOs");
			//int count = (Integer)model.get("count");
			 
			response.setContentType("application/vnd.ms-excel");     
			response.setHeader("Content-disposition", "attachment;filename=\"dayReport.xls\"");  
			HSSFSheet sheet = workbook.createSheet("dayReport");
			
			CellStyle cellStyle = workbook.createCellStyle();
	        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			
			//---设置标题
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("污水站点");
			cell.setCellStyle(cellStyle);
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
			
			
			int rowoffset = 1;
			int titlepoint = 1;
			//设置设备标题
			for (int i = 8; i <= 21; i++) {
				sheet.addMergedRegion(new CellRangeAddress(0,0,rowoffset,rowoffset+1));
				rowoffset = rowoffset + 2;
				HSSFCell titleCell = row.createCell(titlepoint);
				titlepoint = titlepoint + 2;
				titleCell.setCellValue(SystemConfig.equips.get("equipment"+i+"name"));
				titleCell.setCellStyle(cellStyle);
			}
			
			HSSFRow row1 = sheet.createRow(1);
			int rowoffset2 = 1;
			//设置设备子标题：设备运行时间 和停止时间
			for (int i = 8; i <= 21; i++) {
				HSSFCell subtitleCell = row1.createCell(rowoffset2);
				subtitleCell.setCellValue("运行时间");
				subtitleCell.setCellStyle(cellStyle);
				rowoffset2 = rowoffset2 + 1;
				HSSFCell subtitleCel2 = row1.createCell(rowoffset2);
				subtitleCel2.setCellValue("停止时间");
				subtitleCel2.setCellStyle(cellStyle);
				rowoffset2 = rowoffset2 + 1;
			}
			
			
			//设置水质标题
			for (int i = 1; i <= 5; i++) {
				if(i != 4){
					sheet.addMergedRegion(new CellRangeAddress(0,0,rowoffset,rowoffset+2));
					rowoffset = rowoffset + 3;
					HSSFCell titleCell = row.createCell(titlepoint);
					titlepoint = titlepoint + 3;
					titleCell.setCellValue(SystemConfig.detections.get("detection"+i+"name"));
					titleCell.setCellStyle(cellStyle);
				}
			}
			
			//设置子标题：设备水质子标题 ：最大 最小 平均备
			for (int i = 1; i <= 4; i++) {
				HSSFCell subtitleCell = row1.createCell(rowoffset2);
				subtitleCell.setCellValue("最大");
				subtitleCell.setCellStyle(cellStyle);
				rowoffset2 = rowoffset2 + 1;
				HSSFCell subtitleCel2 = row1.createCell(rowoffset2);
				subtitleCel2.setCellValue("最小");
				subtitleCel2.setCellStyle(cellStyle);
				rowoffset2 = rowoffset2 + 1;
				HSSFCell subtitleCel3 = row1.createCell(rowoffset2);
				subtitleCel3.setCellValue("平均");
				subtitleCel3.setCellStyle(cellStyle);
				rowoffset2 = rowoffset2 + 1;
			}
			
			//从第三行开始 前面已经有 0,1
			int controlRowIndex = 2;
			
			
			for (StatisticDayVO statisticDayVO : statisticDayVOs) {
				int controlColIndex = 0;
				
				HSSFRow dataRow = sheet.createRow(controlRowIndex);
				controlRowIndex = controlRowIndex + 1;
				Class<?> statisticDayVOClass = Class.forName("com.yaoli.vo.StatisticDayVO");
				//填充 设备名称
				Method getSewagenameMethod = statisticDayVOClass.getDeclaredMethod("getSewagename");
				String sewagename = getSewagenameMethod.invoke(statisticDayVO).toString();
				HSSFCell datanamecell = dataRow.createCell(controlColIndex);
				datanamecell.setCellValue(sewagename);
				datanamecell.setCellStyle(cellStyle);
				controlColIndex = controlColIndex + 1;
				
				
				
				//填充 设备运行、停止时间
				for (int i = 8; i <= 21; i++) {
					Method method1 = statisticDayVOClass.getDeclaredMethod("getEquip"+i+"normaltime");
					Object normaltimeObject = method1.invoke(statisticDayVO);
					Integer normaltime = (normaltimeObject == null ? 0:Integer.valueOf(normaltimeObject.toString()));
					
					Method method2 = statisticDayVOClass.getDeclaredMethod("getEquip"+i+"abnormaltime");
					Object abnormaltimeObject = method2.invoke(statisticDayVO);
					Integer abnormaltime = (normaltimeObject == null ? 0:Integer.valueOf(abnormaltimeObject.toString()));
					
					HSSFCell datacell = dataRow.createCell(controlColIndex);
					datacell.setCellValue(normaltime);
					datacell.setCellStyle(cellStyle);
					controlColIndex = controlColIndex + 1;
					
					HSSFCell datacel2 = dataRow.createCell(controlColIndex);
					datacel2.setCellValue(abnormaltime);
					datacel2.setCellStyle(cellStyle);
					controlColIndex = controlColIndex + 1;
				}
				
				//填充 水质
				for (int i = 1; i <= 5; i++) {
					if(i != 4){
						Method method1 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"max");
						Object maxObject = method1.invoke(statisticDayVO);
						Double max = maxObject == null ? 0.00:Double.valueOf(String.format("%.2f", Double.valueOf(String.valueOf(maxObject))));

						Method method2 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"min");
						Object minObject = method2.invoke(statisticDayVO);
						Double min = minObject == null ? 0.00:Double.valueOf(String.format("%.2f", Double.valueOf(String.valueOf(minObject))));
						
						Method method3 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"avg");
						Object avgObject = method3.invoke(statisticDayVO);
						Double avg = avgObject == null ? 0.00:Double.valueOf(String.format("%.2f", Double.valueOf(String.valueOf(avgObject))));
						
						HSSFCell datacell = dataRow.createCell(controlColIndex);
						datacell.setCellValue(max);
						datacell.setCellStyle(cellStyle);
						controlColIndex = controlColIndex + 1;
						
						HSSFCell datacel2 = dataRow.createCell(controlColIndex);
						datacel2.setCellValue(min);
						datacel2.setCellStyle(cellStyle);
						controlColIndex = controlColIndex + 1;
						
						HSSFCell datacel3 = dataRow.createCell(controlColIndex);
						datacel3.setCellValue(avg);
						datacel3.setCellStyle(cellStyle);
						controlColIndex = controlColIndex + 1;
					}
				}
			}
		}
		
		
		//面源系统
		//if(systemNO.equals("2")){
			//如果系统参数是0，表明是老系统
			//if(systemNO.equals("0")){
		 if(SystemConfig.site instanceof ImplEquip_1_to_5 && SystemConfig.site instanceof ImplDetect_1_to_5_10_to_15){
			@SuppressWarnings("unchecked")
			List<StatisticDayVO> statisticDayVOs = (List<StatisticDayVO>)model.get("statisticDayVOs");
			 
			response.setContentType("application/vnd.ms-excel");     
			response.setHeader("Content-disposition", "attachment;filename=\"report.xls\"");  
			HSSFSheet sheet = workbook.createSheet("dayReport");
			
			CellStyle cellStyle = workbook.createCellStyle();
	        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        
	        
			CellStyle titleStyle = workbook.createCellStyle();
			titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
			titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        HSSFFont font = workbook.createFont(); 
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 20);
            titleStyle.setFont(font);
	        

	        
	        
			//---设置“污水站点”标题
			HSSFRow row = sheet.createRow(1);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("污水站点");
			cell.setCellStyle(cellStyle);
			sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
			
			//标题 行位移
			int rowoffset = 1;
			int titlepoint = 1;
			//子标题行位移
			int rowoffset2 = 1;
			//填充数据的行控制 从第四行开始 前面已经有 0,1,2
			int controlRowIndex = 3;
			
			//不同的报表类型 excel类型 1表示设备运行记录 设备运行记录不变 跟老系统一致
			// 2表示水质检测 要变
			if(exceltype.equals("1")){
		        //设置 报表 标题：占9列
				HSSFRow titlerow = sheet.createRow(0);
				HSSFCell titlecell = titlerow.createCell(0);
				titlecell.setCellValue(excelnameAndTitleName);
				titlecell.setCellStyle(titleStyle);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
				
				//设置设备标题
				for (int i = 1; i <= 4; i++) {
					//
					sheet.addMergedRegion(new CellRangeAddress(1,1,rowoffset,rowoffset+1));
					rowoffset = rowoffset + 2;
					HSSFCell titleCell = row.createCell(titlepoint);
					titlepoint = titlepoint + 2;
					titleCell.setCellValue(SystemConfig.equips.get("equipment"+i+"name"));
					titleCell.setCellStyle(cellStyle);
				}
				HSSFRow row1 = sheet.createRow(2);
				//设置设备子标题：设备运行时间 和停止时间
				for (int i = 1; i <= 4; i++) {
					HSSFCell subtitleCell = row1.createCell(rowoffset2);
					subtitleCell.setCellValue("运行时间");
					subtitleCell.setCellStyle(cellStyle);
					rowoffset2 = rowoffset2 + 1;
					HSSFCell subtitleCel2 = row1.createCell(rowoffset2);
					subtitleCel2.setCellValue("停止时间");
					subtitleCel2.setCellStyle(cellStyle);
					rowoffset2 = rowoffset2 + 1;
				}
				
				
				//填充设备
				for (StatisticDayVO statisticDayVO : statisticDayVOs) {
					int controlColIndex = 0;
					
					HSSFRow dataRow = sheet.createRow(controlRowIndex);
					controlRowIndex = controlRowIndex + 1;
					Class<?> statisticDayVOClass = Class.forName("com.yaoli.vo.StatisticDayVO");
					//填充 设备名称
					Method getSewagenameMethod = statisticDayVOClass.getDeclaredMethod("getSewagename");
					String sewagename = getSewagenameMethod.invoke(statisticDayVO).toString();
					HSSFCell datanamecell = dataRow.createCell(controlColIndex);
					datanamecell.setCellValue(sewagename);
					datanamecell.setCellStyle(cellStyle);
					controlColIndex = controlColIndex + 1;
					
					//填充 设备运行、停止时间
					for (int i = 1; i <= 4; i++) {
						Method method1 = statisticDayVOClass.getDeclaredMethod("getEquip"+i+"normaltime");
						Object normaltimeObject = method1.invoke(statisticDayVO);
						Integer normaltime = (normaltimeObject == null ? 0:Integer.valueOf(normaltimeObject.toString()));
						
						Method method2 = statisticDayVOClass.getDeclaredMethod("getEquip"+i+"abnormaltime");
						Object abnormaltimeObject = method2.invoke(statisticDayVO);
						Integer abnormaltime = (normaltimeObject == null ? 0:Integer.valueOf(abnormaltimeObject.toString()));
						
						HSSFCell datacell = dataRow.createCell(controlColIndex);
						datacell.setCellValue(normaltime);
						datacell.setCellStyle(cellStyle);
						controlColIndex = controlColIndex + 1;
						
						HSSFCell datacel2 = dataRow.createCell(controlColIndex);
						datacel2.setCellValue(abnormaltime);
						datacel2.setCellStyle(cellStyle);
						controlColIndex = controlColIndex + 1;
					}
				}
				
				//填写制表人和制表时间
				HSSFRow dataRow = sheet.createRow(controlRowIndex);
				
				//制表人 7 8  制表时间9 10 11 12
				HSSFCell datacell1 = dataRow.createCell(4);
				datacell1.setCellValue("制表人");
				datacell1.setCellStyle(cellStyle);
				
				HSSFCell datacell2 = dataRow.createCell(5);
				datacell2.setCellValue((String)model.get("username"));
				datacell2.setCellStyle(cellStyle);
				
				//制表时间
				HSSFCell datacell3 = dataRow.createCell(6);
				datacell3.setCellValue("制表时间");
				datacell3.setCellStyle(cellStyle);
				
				//制表时间
				HSSFCell datacell4 = dataRow.createCell(7);
				datacell4.setCellValue((String)model.get("time"));
				datacell4.setCellStyle(cellStyle);
				sheet.addMergedRegion(new CellRangeAddress(controlRowIndex, controlRowIndex, 7, 8));
				controlRowIndex = controlRowIndex + 1;
			}else if(exceltype.equals("2")){//水质检测要变 添加了几个参数
		        //设置 报表 标题：占9列
				HSSFRow titlerow = sheet.createRow(0);
				HSSFCell titlecell = titlerow.createCell(0);
				titlecell.setCellValue(excelnameAndTitleName);
				titlecell.setCellStyle(titleStyle);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 27));
				
				//设置水质标题
				for (int i = 1; i <= 5; i++) {
					if(i != 4){
						//这里一定要注意
						sheet.addMergedRegion(new CellRangeAddress(1,1,rowoffset,rowoffset+2));
						rowoffset = rowoffset + 3;
						HSSFCell titleCell = row.createCell(titlepoint);
						titlepoint = titlepoint + 3;
						titleCell.setCellValue(SystemConfig.detections.get("detection"+i+"name"));
						titleCell.setCellStyle(cellStyle);
					}
				}
				for (int i = 10; i <= 15; i++) {
					//这里一定要注意
					sheet.addMergedRegion(new CellRangeAddress(1,1,rowoffset,rowoffset+2));
					rowoffset = rowoffset + 3;
					HSSFCell titleCell = row.createCell(titlepoint);
					titlepoint = titlepoint + 3;
					titleCell.setCellValue(SystemConfig.detections.get("detection"+i+"name"));
					titleCell.setCellStyle(cellStyle);
				}
				//----以上设置水质标题
				
				
				
				HSSFRow row1 = sheet.createRow(2);
				//设置子标题：设备水质子标题 ：最大 最小 平均备
				for (int i = 1; i <= 9; i++) { //这里有9个水质 所以<=9
					//这里一定要注意
					HSSFCell subtitleCell = row1.createCell(rowoffset2);
					subtitleCell.setCellValue("最大");
					subtitleCell.setCellStyle(cellStyle);
					rowoffset2 = rowoffset2 + 1;
					HSSFCell subtitleCel2 = row1.createCell(rowoffset2);
					subtitleCel2.setCellValue("最小");
					subtitleCel2.setCellStyle(cellStyle);
					rowoffset2 = rowoffset2 + 1;
					HSSFCell subtitleCel3 = row1.createCell(rowoffset2);
					subtitleCel3.setCellValue("平均");
					subtitleCel3.setCellStyle(cellStyle);
					rowoffset2 = rowoffset2 + 1;
				}
				
				//填充水质
				for (StatisticDayVO statisticDayVO : statisticDayVOs) {
					int controlColIndex = 0;
					
					HSSFRow dataRow = sheet.createRow(controlRowIndex);
					controlRowIndex = controlRowIndex + 1;
					Class<?> statisticDayVOClass = Class.forName("com.yaoli.vo.StatisticDayVO");
					//填充 设备名称
					Method getSewagenameMethod = statisticDayVOClass.getDeclaredMethod("getSewagename");
					String sewagename = getSewagenameMethod.invoke(statisticDayVO).toString();
					HSSFCell datanamecell = dataRow.createCell(controlColIndex);
					datanamecell.setCellValue(sewagename);
					datanamecell.setCellStyle(cellStyle);
					controlColIndex = controlColIndex + 1;
					
					//填充 水质
					for (int i = 1; i <= 5; i++) {
						if(i != 4){
							Method method1 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"max");
							Object maxObject = method1.invoke(statisticDayVO);
							Double max = maxObject == null ? 0.00:Double.valueOf(String.format("%.2f", Double.valueOf(String.valueOf(maxObject))));

							Method method2 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"min");
							Object minObject = method2.invoke(statisticDayVO);
							Double min = minObject == null ? 0.00:Double.valueOf(String.format("%.2f", Double.valueOf(String.valueOf(minObject))));
							
							Method method3 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"avg");
							Object avgObject = method3.invoke(statisticDayVO);
							Double avg = avgObject == null ? 0.00:Double.valueOf(String.format("%.2f", Double.valueOf(String.valueOf(avgObject))));
							
							HSSFCell datacell = dataRow.createCell(controlColIndex);
							datacell.setCellValue(max);
							datacell.setCellStyle(cellStyle);
							controlColIndex = controlColIndex + 1;
							
							HSSFCell datacel2 = dataRow.createCell(controlColIndex);
							datacel2.setCellValue(min);
							datacel2.setCellStyle(cellStyle);
							controlColIndex = controlColIndex + 1;
							
							HSSFCell datacel3 = dataRow.createCell(controlColIndex);
							datacel3.setCellValue(avg);
							datacel3.setCellStyle(cellStyle);
							controlColIndex = controlColIndex + 1;
						}
					}
					
					//填充 水质
					for (int i = 10; i <= 15; i++) {
						Method method1 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"max");
						Object maxObject = method1.invoke(statisticDayVO);
						Double max = maxObject == null ? 0.00:Double.valueOf(String.format("%.2f", Double.valueOf(String.valueOf(maxObject))));

						Method method2 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"min");
						Object minObject = method2.invoke(statisticDayVO);
						Double min = minObject == null ? 0.00:Double.valueOf(String.format("%.2f", Double.valueOf(String.valueOf(minObject))));
						
						Method method3 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"avg");
						Object avgObject = method3.invoke(statisticDayVO);
						Double avg = avgObject == null ? 0.00:Double.valueOf(String.format("%.2f", Double.valueOf(String.valueOf(avgObject))));
						
						HSSFCell datacell = dataRow.createCell(controlColIndex);
						datacell.setCellValue(max);
						datacell.setCellStyle(cellStyle);
						controlColIndex = controlColIndex + 1;
						
						HSSFCell datacel2 = dataRow.createCell(controlColIndex);
						datacel2.setCellValue(min);
						datacel2.setCellStyle(cellStyle);
						controlColIndex = controlColIndex + 1;
						
						HSSFCell datacel3 = dataRow.createCell(controlColIndex);
						datacel3.setCellValue(avg);
						datacel3.setCellStyle(cellStyle);
						controlColIndex = controlColIndex + 1;
					}
				}
				//填写制表人和制表时间
				HSSFRow dataRow = sheet.createRow(controlRowIndex);
				
				//制表人 7 8  制表时间9 10 11 12
				HSSFCell datacell1 = dataRow.createCell(22);
				datacell1.setCellValue("制表人");
				datacell1.setCellStyle(cellStyle);
				
				HSSFCell datacell2 = dataRow.createCell(23);
				datacell2.setCellValue((String)model.get("username"));
				datacell2.setCellStyle(cellStyle);
				
				//制表时间
				HSSFCell datacell3 = dataRow.createCell(24);
				datacell3.setCellValue("制表时间");
				datacell3.setCellStyle(cellStyle);
				
				//制表时间
				HSSFCell datacell4 = dataRow.createCell(25);
				datacell4.setCellValue((String)model.get("time"));
				datacell4.setCellStyle(cellStyle);
				sheet.addMergedRegion(new CellRangeAddress(controlRowIndex, controlRowIndex, 25, 27));
				controlRowIndex = controlRowIndex + 1;
			}
		}
	 } 
}
