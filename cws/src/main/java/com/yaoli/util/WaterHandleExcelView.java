package com.yaoli.util;

import com.yaoli.beans.Sewage;
import com.yaoli.config.SystemConfig;
import com.yaoli.config.siteFunction.ImplDetect_1_to_5;
import com.yaoli.config.siteFunction.ImplEquip_6_to_21;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 污水处理报表导出功能更
 * Created by will on 2016/10/7.
 */

public class WaterHandleExcelView extends AbstractExcelView {
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



        // 老系统
        //if(systemNO.equals("0"))
        if (SystemConfig.site instanceof ImplEquip_6_to_21 && SystemConfig.site instanceof ImplDetect_1_to_5) {
                @SuppressWarnings("unchecked")
                List<Sewage> sewageList = (List<Sewage>) model.get("sewageList");

                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-disposition", "attachment;filename=\"" + encodeExcelnameAndTitleName + ".xls\"");
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


                //设置 报表 标题：占5列
                HSSFRow titlerow = sheet.createRow(0);
                HSSFCell titlecell = titlerow.createCell(0);
                titlecell.setCellValue(excelnameAndTitleName);
                titlecell.setCellStyle(titleStyle);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));


                //---设置 标题 行
                HSSFRow row = sheet.createRow(1);
                HSSFCell cell = row.createCell(0);
                cell.setCellValue("污水站点名" +
                        "称");
                cell.setCellStyle(cellStyle);

                HSSFCell cell_1 = row.createCell(1);
                cell_1.setCellValue("处理水量");
                cell_1.setCellStyle(cellStyle);

                HSSFCell cell_2 = row.createCell(2);
                cell_2.setCellValue("削减COD量");
                cell_2.setCellStyle(cellStyle);

                HSSFCell cell_3 = row.createCell(3);
                cell_3.setCellValue("削减NH3-N量");
                cell_3.setCellStyle(cellStyle);

                HSSFCell cell_4 = row.createCell(4);
                cell_4.setCellValue("削减总P量");
                cell_4.setCellStyle(cellStyle);


                int controlRowIndex = 2;

                DecimalFormat df = new DecimalFormat("0.##");

                for (Sewage sewage : sewageList) {
                    //填写制表人和制表时间
                    HSSFRow dataRow = sheet.createRow(controlRowIndex);

                    HSSFCell data_cell_0 = dataRow.createCell(0);
                    data_cell_0.setCellValue(sewage.getName());
                    data_cell_0.setCellStyle(cellStyle);

                    HSSFCell data_cell_1 = dataRow.createCell(1);
                    data_cell_1.setCellValue(String.valueOf(df.format(sewage.getWaterflow())));
                    data_cell_1.setCellStyle(cellStyle);

                    HSSFCell data_cell_2 = dataRow.createCell(2);
                    data_cell_2.setCellValue(String.valueOf(df.format(sewage.getReducecod())));
                    data_cell_2.setCellStyle(cellStyle);

                    HSSFCell data_cell_3 = dataRow.createCell(3);
                    data_cell_3.setCellValue(String.valueOf(df.format(sewage.getReducenh3n())));
                    data_cell_3.setCellStyle(cellStyle);

                    HSSFCell data_cell_4 = dataRow.createCell(4);
                    data_cell_4.setCellValue(String.valueOf(df.format(sewage.getReducep())));
                    data_cell_4.setCellStyle(cellStyle);

                    controlRowIndex++;
                }


                //填写制表人和制表时间
                HSSFRow dataRow = sheet.createRow(controlRowIndex);

                //制表人 7 8  制表时间9 10 11 12
                HSSFCell datacell1 = dataRow.createCell(2);
                datacell1.setCellValue("制表人");
                datacell1.setCellStyle(cellStyle);

                HSSFCell datacell2 = dataRow.createCell(3);
                datacell2.setCellValue((String) model.get("username"));
                datacell2.setCellStyle(cellStyle);

                //制表时间
                HSSFCell datacell3 = dataRow.createCell(4);
                datacell3.setCellValue("制表时间");
                datacell3.setCellStyle(cellStyle);

                //制表时间
                HSSFCell datacell4 = dataRow.createCell(5);
                datacell4.setCellValue((String) model.get("time"));
                datacell4.setCellStyle(cellStyle);
            }
    }
}
