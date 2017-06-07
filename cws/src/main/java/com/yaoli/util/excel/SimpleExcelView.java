package com.yaoli.util.excel;

import com.yaoli.util.excel.object.ExcelContentObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by will on 2016/11/26.
 *
 * 简单标题 Excel
 */
public class SimpleExcelView extends AbstractExcelView {
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //Map<String, String> map = CustomPropertyConfigurer.getProperties();


        ExcelContentObject content = (ExcelContentObject)model.get("view");


        //设置制作时间
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);

        //设置标题名称
        String headerName = content.getHeader();
        String encodeExcelnameAndTitleName = java.net.URLEncoder.encode(headerName,"utf-8");

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=\""+encodeExcelnameAndTitleName+".xls\"");


        HSSFSheet sheet = workbook.createSheet(headerName);

        //普通样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        //header样式
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 20);
        headerStyle.setFont(font);


        //行控制 从 1 开始
        int rowControlIndex = 0;


        //填充header数据
        HSSFRow headerrow = sheet.createRow(rowControlIndex);
        HSSFCell titlecell = headerrow.createCell(0);
        titlecell.setCellValue(headerName);
        titlecell.setCellStyle(headerStyle);
        //header所占的列应该是1 + 标题列的大小
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, content.getTitles().size()));

        rowControlIndex ++;//加1


        //此列是标题行
        HSSFRow titleRow = sheet.createRow(rowControlIndex);

        //---设置 mainTitle 为第一个
        HSSFCell cell = titleRow.createCell(0);
        cell.setCellValue(content.getMainTitle());
        cell.setCellStyle(cellStyle);
        //sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));

        //---设置 数据标题，即从第二列开始的标题
        for (int i = 0; i < content.getTitles().size(); i++) {
            HSSFCell titleCell = titleRow.createCell(i+1);
            titleCell.setCellValue(content.getTitles().get(i));
            titleCell.setCellStyle(cellStyle);
        }

        rowControlIndex++;

        //以下开始填充数据 mainData.size() 表示的是行数据，即一共有多少条数据
        for(int i = 0 ; i < content.getMainData().size(); i++){

            HSSFRow dataRow = sheet.createRow(rowControlIndex);

            HSSFCell dataCell = null;
            //+1 表示 表示的是 列数据
            for(int j = 0 ; j < content.getTitles().size()+1; j++){
                dataCell = dataRow.createCell(j);

                dataCell.setCellStyle(cellStyle);

                //开始填充数据
                if(j == 0){ //第一列
                    //索引是i;
                    dataCell.setCellValue(content.getMainData().get(i));

                }else{//第二列开始 减去1 是为了与 content.getData().size() + 1 相对应

                    dataCell.setCellValue(content.getData().get(i).get(j - 1));

                }
            }

            rowControlIndex = rowControlIndex + 1;

        }
        //填充数据结束

        //填写制表人和制表时间
        HSSFRow dataRow = sheet.createRow(rowControlIndex);


        int point = content.getTitles().size();

        if(point >= 5){
            //制表人
            HSSFCell datacell1 = dataRow.createCell(point - 4);
            datacell1.setCellValue("制表人");
            datacell1.setCellStyle(cellStyle);

            HSSFCell datacell2 = dataRow.createCell(point - 3);
            datacell2.setCellValue(content.getAuthor());
            datacell2.setCellStyle(cellStyle);

            //制表时间
            HSSFCell datacell3 = dataRow.createCell(point - 2);
            datacell3.setCellValue("制表时间");
            datacell3.setCellStyle(cellStyle);

            HSSFCell datacell4 = dataRow.createCell(point - 1);
            datacell4.setCellValue(dateString);
            datacell4.setCellStyle(cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(rowControlIndex, rowControlIndex, point - 1, point));
        }else{
            //制表人
            HSSFCell datacell1 = dataRow.createCell(0);
            datacell1.setCellValue("制表人");
            datacell1.setCellStyle(cellStyle);

            HSSFCell datacell2 = dataRow.createCell(1);
            datacell2.setCellValue(content.getAuthor());
            datacell2.setCellStyle(cellStyle);

            //制表时间
            HSSFCell datacell3 = dataRow.createCell(2);
            datacell3.setCellValue("制表时间");
            datacell3.setCellStyle(cellStyle);

            HSSFCell datacell4 = dataRow.createCell(3);
            datacell4.setCellValue(dateString);
            datacell4.setCellStyle(cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(rowControlIndex, rowControlIndex, 3, 4));
        }


    }
}
