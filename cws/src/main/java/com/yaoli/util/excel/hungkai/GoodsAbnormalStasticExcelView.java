package com.yaoli.util.excel.hungkai;

import com.yaoli.beans.GoodsAbnormalStatisticResult;
import com.yaoli.util.excel.object.SubExcelContentObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用于复杂表头，比如设备运行报表，水质监测报表
 * <p>
 * 具体表现为，表头有两行数据，有父表头，和子标题
 */
public class GoodsAbnormalStasticExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubExcelContentObject content = (SubExcelContentObject) model.get("view");
        // 设置制作时间
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);

        //获取查询的时间和来源创建Excel文件名
        String flag = (String) model.get("flag");
        String time = (String) model.get("time");
        String origin = (String) model.get("origin");
        //获取数据用于接下来的填充表格
        List<GoodsAbnormalStatisticResult> data = (List<GoodsAbnormalStatisticResult>) model.get("data");

        // 设置Excel文件名
        String fileName = null;
        String timeFormat = null;
        if ("month".equals(flag)) {//年度统计报表
            String[] temp = time.split("-");
            timeFormat = temp[0] + "年" + temp[1] + "月";
        } else {//月度统计报表
            timeFormat = time + "年";
        }
        if (origin != null) {
            fileName = origin + timeFormat + "物品故障汇总统计报表";
        } else {
            fileName = timeFormat + "物品故障汇总统计报表";
        }
        String encodeExcelFileName = java.net.URLEncoder.encode(fileName, "utf-8");//编码成UTF-8格式的字符串
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + encodeExcelFileName + ".xls");

        // 1.创建表单，并设置列数和列宽
        HSSFSheet sheet = workbook.createSheet(fileName);
        sheet.setColumnWidth(0, 6500);
        sheet.setColumnWidth(1, 6500);
        sheet.setColumnWidth(2, 6500);
        sheet.setColumnWidth(3, 6500);
        sheet.setColumnWidth(4, 6500);


        // 设置字体
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("黑体");
        headfont.setFontHeightInPoints((short) 22);// 字体大小
        headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        // 另一个样式
        HSSFCellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        headstyle.setLocked(true);
        headstyle.setWrapText(true);// 自动换行

        // 另一个字体样式
        HSSFFont columnHeadFont = workbook.createFont();
        columnHeadFont.setFontName("宋体");
        columnHeadFont.setFontHeightInPoints((short) 10);
        columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // 列头的样式
        HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
        columnHeadStyle.setFont(columnHeadFont);
        columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        columnHeadStyle.setLocked(true);
        columnHeadStyle.setWrapText(true);
        columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
        columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
        columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
        columnHeadStyle.setBorderRight((short) 1);// 边框的大小
        columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
        // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
        columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);

        HSSFCellStyle footer = workbook.createCellStyle();
        footer.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        footer.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        footer.setLocked(true);
        footer.setWrapText(true);// 自动换行


        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        // 普通单元格样式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 上下居中
        style.setWrapText(true);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft((short) 1);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight((short) 1);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．
        // 另一个样式
        HSSFCellStyle centerstyle = workbook.createCellStyle();
        centerstyle.setFont(font);
        centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        centerstyle.setWrapText(true);
        centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
        centerstyle.setBorderLeft((short) 1);
        centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
        centerstyle.setBorderRight((short) 1);
        centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 设置单元格的边框为粗体
        centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．

        try {
            // 创建第一行
            HSSFRow row0 = sheet.createRow(0);
            // 设置行高
            row0.setHeight((short) 900);

            // 创建第一列
            HSSFCell cell0 = row0.createCell(0);
            cell0.setCellValue(new HSSFRichTextString(fileName));
            cell0.setCellStyle(headstyle);
            /**
             * 合并单元格 第一个参数：第一个单元格的行数（从0开始） 第二个参数：第二个单元格的行数（从0开始）
             * 第三个参数：第一个单元格的列数（从0开始） 第四个参数：第二个单元格的列数（从0开始）
             */
            CellRangeAddress range = new CellRangeAddress(0, 0, 0, 4);
            sheet.addMergedRegion(range); //合并第一行

            // 第二行
            HSSFRow row2 = sheet.createRow(1);
            row2.setHeight((short) 500);
            HSSFCell cell = row2.createCell(0);
            cell.setCellValue(new HSSFRichTextString("物品名称"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(1);
            cell.setCellValue(new HSSFRichTextString("待监数量"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(2);
            cell.setCellValue(new HSSFRichTextString("在处理数量"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(3);
            cell.setCellValue(new HSSFRichTextString("报废数量"));
            cell.setCellStyle(columnHeadStyle);
            cell = row2.createCell(4);
            cell.setCellValue(new HSSFRichTextString("维修完成数量"));
            cell.setCellStyle(columnHeadStyle);

            // 获取数据，填充数据
            for (int j = 2; j < 2 + data.size(); j++) {
                HSSFRow row = sheet.createRow(j);
                row.setHeight((short) 400);
                cell = row.createCell(0);
                cell.setCellValue(new HSSFRichTextString(data.get(j - 2).getName()));//物品名称
                cell.setCellStyle(centerstyle);
                cell = row.createCell(1);
                cell.setCellValue(data.get(j - 2).getAwaitdetectionamounts());//待监数量
                cell.setCellStyle(centerstyle);
                cell = row.createCell(2);
                cell.setCellValue(data.get(j - 2).getProcessingamounts());//在处理数量
                cell.setCellStyle(centerstyle);
                cell = row.createCell(3);
                cell.setCellValue(data.get(j - 2).getScrapedamounts());//报废数量
                cell.setCellStyle(centerstyle);
                cell = row.createCell(4);
                cell.setCellValue(data.get(j - 2).getCompletedamounts());//
                cell.setCellStyle(centerstyle);
            }

            // 列尾
            int footRownumber = sheet.getLastRowNum();
            HSSFRow footRow = sheet.createRow(footRownumber + 1);
            HSSFCell footRowcell = footRow.createCell(0);
            footRow.setHeight((short) 400);
            footRowcell.setCellStyle(footer);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            String downloadTime = simpleDateFormat.format(new Date());
            footRowcell.setCellValue(new HSSFRichTextString(
                    "部门负责人：                                  时 间： " + downloadTime));
            range = new CellRangeAddress(footRownumber + 1, footRownumber + 1,
                    0, 4);
            sheet.addMergedRegion(range);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
