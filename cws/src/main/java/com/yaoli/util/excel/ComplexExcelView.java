package com.yaoli.util.excel;

/**
 * Created by will on 2016/11/26.
 */

import com.yaoli.util.excel.object.SubExcelContentObject;
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
 * 用于复杂表头，比如设备运行报表，水质监测报表
 *
 * 具体表现为，表头有两行数据，有父表头，和子标题
 */
public class ComplexExcelView extends AbstractExcelView {
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //Map<String, String> map = CustomPropertyConfigurer.getProperties();


        SubExcelContentObject content = (SubExcelContentObject)model.get("view");


        //设置制作时间
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);

        //设置标题名称
        String headerName = content.getHeader();
        String encodeExcelnameAndTitleName = java.net.URLEncoder.encode(headerName,"utf-8");

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=\""+encodeExcelnameAndTitleName+dateString+".xls\"");


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
        titlecell.setCellValue(headerName+dateString);
        titlecell.setCellStyle(headerStyle);
        //header所占的列应该是1 + 标题列的大小
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, content.getTitles().size()*content.getSubTitles().size()));

        rowControlIndex ++;//加1


        //此列是标题行
        HSSFRow titleRow = sheet.createRow(rowControlIndex);

        //---设置 mainTitle 为第一列第一个标题，该标题占两列
        HSSFCell cell = titleRow.createCell(0);
        cell.setCellValue(content.getMainTitle());
        cell.setCellStyle(cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowControlIndex, rowControlIndex + 1, 0, 0));

        //---设置 数据标题，即从第二列开始的父标题

        int columnControlIndex = 1; //第二列
        for (int i = 0; i < content.getTitles().size(); i++) {
            HSSFCell titleCell = titleRow.createCell(columnControlIndex);
            titleCell.setCellValue(content.getTitles().get(i));
            //
            //System.out.println(content.getTitles().get(i));
            titleCell.setCellStyle(cellStyle);

            //System.out.println( columnControlIndex );
            //System.out.println( columnControlIndex + content.getSubTitles().size() - 1);

            //合并
            sheet.addMergedRegion(new CellRangeAddress(rowControlIndex, rowControlIndex ,
                    columnControlIndex, columnControlIndex + content.getSubTitles().size() - 1));
            //索引
            columnControlIndex = columnControlIndex + content.getSubTitles().size();

            //System.out.println( columnControlIndex );
        }

        rowControlIndex = rowControlIndex + 1;

        //填充子标题
        HSSFRow subTitleRow = sheet.createRow(rowControlIndex);

        for(int i = 0 ; i < content.getTitles().size(); i ++){
            for(int j = 0 ; j < content.getSubTitles().size() ; j++){
                HSSFCell titleCell = subTitleRow.createCell(i+1);
                titleCell.setCellValue(content.getTitles().get(i));
                titleCell.setCellStyle(cellStyle);
            }
        }

        //填充子标题，数量是 标题的数量 * 子标题的数量
        //比如设备运行报表中，父标题 4 个，子标题 2个，这样一共有 2*4 = 8个标题
        for(int i = 0 ; i < content.getTitles().size() * content.getSubTitles().size(); i ++){

            HSSFCell titleCell = subTitleRow.createCell((i + 1));

            int temp = (i % content.getSubTitles().size());

            titleCell.setCellValue(content.getSubTitles().get(temp));

            titleCell.setCellStyle(cellStyle);
        }

        rowControlIndex = rowControlIndex + 1;




        //以下开始填充数据 mainData.size() 表示的是行数据，即一共有多少条数据
        for(int i = 0 ; i < content.getMainData().size(); i++){

            HSSFRow dataRow = sheet.createRow(rowControlIndex);

            HSSFCell dataCell = null;
            //+1 表示 表示的是 列数据
            for(int j = 0 ; j < content.getTitles().size()*content.getSubTitles().size() + 1; j++){
                dataCell = dataRow.createCell(j);

                dataCell.setCellStyle(cellStyle);

                //开始填充数据
                if(j == 0){ //第一列
                    //索引是i;
                    dataCell.setCellValue(content.getMainData().get(i));

                }else{
                    String cellValue = content.getSubData().
                            get( (j % content.getTitles().size()) ).
                            get( (j % content.getSubTitles().size()) ).
                            get( i );

                    dataCell.setCellValue(cellValue);
                }
            }

            rowControlIndex = rowControlIndex + 1;

        }
        //填充数据结束

        //填写制表人和制表时间
        HSSFRow dataRow = sheet.createRow(rowControlIndex);


        int point = content.getTitles().size()*content.getSubTitles().size();

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
