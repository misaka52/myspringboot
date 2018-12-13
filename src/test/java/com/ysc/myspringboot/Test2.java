package com.ysc.myspringboot;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Test2 {

    @Test
    public void testXlsx() throws Exception {
//        File file = new File("");
//        System.out.println(file.exists());
//        InputStream inputStream =inputStream new FileInputStream(file);
        // 2003 excel
//        Workbook workbook = new HSSFWorkbook();

        //2007 excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
//        sheet.setDefaultColumnWidth(20);

//        sheet.setDefaultRowHeight((short)100);

        //style
        CellStyle cellStyle = workbook.createCellStyle();

        //title
        String[] title = {"姓名", "年龄fdsafdsafdsafdsafdsaffdsafdsaf", "学号"};
        Row titleRow = sheet.createRow(0);
        for (int i = 0; i < title.length; ++i) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(title[i]);
        }

        //context
        String[][] context = {{"ysc", "221231321321", "fsduafkdsfkdsajfdslkajflkdsajflksajfs"},
                {"gf", "21", "12321"},
                {"si", "21", "1233213"}};
        for (int i = 0; i < context.length; ++i) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < context[i].length; ++j) {
                row.createCell(j).setCellValue(context[i][j]);
            }
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);

        //save
        FileOutputStream fileOutputStream = new FileOutputStream("test.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
    }

    public void exportExcel(Workbook workbook, String[] head, String[][] body, String fileName) {
        // sheet，默认一个excel一页，后续若有需求再做调整
        Sheet sheet = workbook.createSheet();

        // head
        Row titleRow = sheet.createRow(0);
        int columnLen = head.length;
        for (int i = 0; i < head.length; ++i) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(head[i]);
        }

        // body
        int rowLen = body.length;
        for (int i = 0; i < rowLen; ++i) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < columnLen; ++j) {
                row.createCell(j).setCellValue(body[i][j]);
            }
        }

        // 使得列宽随内容长短变动
        for (int i = 0; i < columnLen; ++i) {
            sheet.autoSizeColumn(i);
//            sheet.setColumnWidth(i, (int)(sheet.getColumnWidth(i) * 1.7));
        }

        // save
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName + ".xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
