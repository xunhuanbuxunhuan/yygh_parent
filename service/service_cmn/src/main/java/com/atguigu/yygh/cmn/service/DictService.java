package com.atguigu.yygh.cmn.service;


import com.atguigu.yygh.model.cmn.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {

    List<Dict> findByParentId(Long parentId);

    boolean isChildren(Long id);

    //导出数据接口
    void exportData(HttpServletResponse response);

    //导入数据接口
    void importData(MultipartFile file);



}
