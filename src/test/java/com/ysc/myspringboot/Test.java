package com.ysc.myspringboot;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        String[] title = {"角色", "编号", "功能名称", "创建课程"};
        String[][] context={{"UC11","设置课程","创建课程"},
                {"UC12","设置学生名单","给出与课程关联的学生名单"},
                {"UC21","查看学生名单",""},
                {"UC22","查看小组信息","显示助教所负责的小组列表信息"}};
        try {
            WritableWorkbook book = Workbook.createWorkbook(new File("C:\\Users\\yuanshancheng\\Desktop\\test.xls"));
            WritableSheet writableSheet = book.createSheet("第一页", 0);

            for (int i = 0; i < 4; ++i) {
                writableSheet.addCell(new Label(i, 0, title[i]));
            }
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 3; ++j) {
                    writableSheet.addCell(new Label(j + 1, i + 1, context[i][j]));
                }
            }
            writableSheet.addCell(new Label(0, 1, "教师"));
            writableSheet.addCell(new Label(0, 3, "助教"));
            writableSheet.mergeCells(0, 1, 0, 2);
            writableSheet.mergeCells(0,3,0,4);
            writableSheet.insertColumn(0);
            writableSheet.addCell(new Label(0, 4, "合计"));

            book.write();
            book.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}