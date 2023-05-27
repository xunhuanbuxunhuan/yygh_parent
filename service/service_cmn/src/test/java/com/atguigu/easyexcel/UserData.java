package com.atguigu.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author fengwenhao
 * @Date 2023/5/24 21:08
 */
@Data
public class UserData {

    @ExcelProperty("学生编号")
    private int uid;

    @ExcelProperty("学生名字")
    private String username;
}
