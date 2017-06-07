package com.yaoli.util;

import com.yaoli.vo.WarmingDayLogVO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by will on 2016/9/19.
 *
 * 该类原来用于报警日志报表，
 * 现在改类废弃
 * 改用ExcelContentObject填充数据
 *
 */
@Deprecated
public class WarmingLogStatisticExcelView extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> titleList = new ArrayList<String>();
        titleList.add("污水站点名称");
        titleList.add("运营编号");
        titleList.add("水质异常次数");
        titleList.add("断电断线次数");
        titleList.add("曝气机故障次数");
        titleList.add("水泵故障次数");

        //Map<String, String> map = CustomPropertyConfigurer.getProperties();

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);

        String excelnameAndTitleName = model.get("reportname")+dateString;
        String encodeExcelnameAndTitleName = java.net.URLEncoder.encode(excelnameAndTitleName,"utf-8");

        @SuppressWarnings("unchecked")
        List<WarmingDayLogVO> data = ( List<WarmingDayLogVO>)model.get("data");

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

        //sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));

        //填充数据的行控制 从第三行开始 前面已经有 0,1
        int controlRowIndex = 2;

        //设置 报表 标题：占9列
        HSSFRow titlerow = sheet.createRow(0);
        HSSFCell titlecell = titlerow.createCell(0);
        titlecell.setCellValue(excelnameAndTitleName);
        titlecell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));


        HSSFRow row = sheet.createRow(1);
        ////设置数据项目标题
        for (int i = 0; i <= 5; i++) {
            HSSFCell titleCell = row.createCell(i);
            titleCell.setCellValue(titleList.get(i));
            titleCell.setCellStyle(cellStyle);
        }


        //填充数据
        for (WarmingDayLogVO vo : data) {
            HSSFRow dataRow = sheet.createRow(controlRowIndex);
            controlRowIndex = controlRowIndex + 1;

            String sewageName = vo.getSewageName();
            String operationnum = vo.getOperationnum();

            int detection_count = vo.getDetection_count();
            int withoutElectric_count = vo.getWithoutElectric_count();
            int equip1_count = vo.getEquip1_count();
            int equip25_count = vo.getEquip25_count();



            HSSFCell sewageNamedataCell = dataRow.createCell(0);
            sewageNamedataCell.setCellValue(sewageName);
            sewageNamedataCell.setCellStyle(cellStyle);

            HSSFCell operationnumCell = dataRow.createCell(1);
            operationnumCell.setCellValue(operationnum);
            operationnumCell.setCellStyle(cellStyle);

            HSSFCell detection_countCell = dataRow.createCell(2);
            detection_countCell.setCellValue(detection_count);
            detection_countCell.setCellStyle(cellStyle);

            HSSFCell withoutElectric_countCell = dataRow.createCell(3);
            withoutElectric_countCell.setCellValue(withoutElectric_count);
            withoutElectric_countCell.setCellStyle(cellStyle);

            HSSFCell equip1_countCell = dataRow.createCell(4);
            equip1_countCell.setCellValue(equip1_count);
            equip1_countCell.setCellStyle(cellStyle);

            HSSFCell equip25_countCell = dataRow.createCell(5);
            equip25_countCell.setCellValue(equip25_count);
            equip25_countCell.setCellStyle(cellStyle);
        }
        //填写制表人和制表时间
        HSSFRow dataRow = sheet.createRow(controlRowIndex);

        //制表人 7 8  制表时间9 10 11 12
        HSSFCell datacell1 = dataRow.createCell(1);
        datacell1.setCellValue("制表人");
        datacell1.setCellStyle(cellStyle);

        HSSFCell datacell2 = dataRow.createCell(2);
        datacell2.setCellValue((String)model.get("username"));
        datacell2.setCellStyle(cellStyle);

        //制表时间
        HSSFCell datacell3 = dataRow.createCell(3);
        datacell3.setCellValue("制表时间");
        datacell3.setCellStyle(cellStyle);

        //制表时间
        HSSFCell datacell4 = dataRow.createCell(4);
        datacell4.setCellValue((String)model.get("time"));
        datacell4.setCellStyle(cellStyle);
    }
}
