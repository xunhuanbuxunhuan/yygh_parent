package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.nio.file.Path;

/**
 * @Description TODO
 * @Author fengwenhao
 * @Date 2023/5/24 21:30
 */
public class TestRead {
    public static void main(String[] args) {

        String PATH="D:\\javacode\\spring\\shangyitong\\yygh_parent\\service\\service_cmn";
        //文件路径
        String fileName= PATH+"01.xlsx";
        EasyExcel.read(fileName,UserData.class,new ExcelListener()).sheet().doRead();

    }
}
