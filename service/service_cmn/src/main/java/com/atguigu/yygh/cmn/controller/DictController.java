package com.atguigu.yygh.cmn.controller;

import com.atguigu.yygh.cmn.service.DictService;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description TODO
 * @Author fengwenhao
 * @Date 2023/5/22 22:02
 */

@CrossOrigin
@Api("数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")

public class DictController {

    @Autowired
    private DictService dictService;

    @GetMapping("/findByParentId/{parentId}")
    public Result<List<Dict>>findByParentId(@PathVariable Long parentId){
        List<Dict>list=dictService.findByParentId(parentId);
        return Result.ok(list);
    }

    @ApiOperation("导出")
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response){
        dictService.exportData(response);
    }

    @ApiOperation(("导入"))
    @PostMapping("/importData")
    public Result importData(MultipartFile file){
        dictService.importData(file);
        return Result.ok();
    }

}
