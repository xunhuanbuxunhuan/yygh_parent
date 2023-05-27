package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author fengwenhao
 * @Date 2023/5/24 21:09
 */
public class TestWrite {
    public static void main(String[] args) {
        String PATH="D:\\javacode\\spring\\shangyitong\\yygh_parent\\service\\service_cmn";

        //构建数据list集合
        List<UserData> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserData userData = new UserData();
            userData.setUid(i);
            userData.setUsername("lucy"+i);
            list.add(userData);
        }

        //设置excel文件路径和文件名称
        String fileName=PATH+"01.xlsx";
        //调用方法实现写操作
        EasyExcel.write(fileName,UserData.class).sheet("学生信息")
                .doWrite(list);
    }
}
