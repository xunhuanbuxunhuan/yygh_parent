package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.hosp.service.HospitalSetService;
import com.atguigu.yygh.model.hosp.Hospital;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author fengwenhao
 * @Date 2023/5/13 17:24
 */

@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@CrossOrigin //解决跨域问题
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation(value = "查询所有医院设置")
    @GetMapping("/findAll")
    public Result findAll(){
        return Result.ok(hospitalSetService.list());
    }


    @ApiOperation("逻辑删除医院设置")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if(!flag){
            return Result.fail();
        }
        return Result.ok();
    }

    //分页多条件查询
    @PostMapping("/findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable Long current, @PathVariable Long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
        Page<HospitalSet> hospitalSetPage = new Page<>(current,limit);
        LambdaQueryWrapper<HospitalSet> lqw = new LambdaQueryWrapper<>();
        lqw.like(null!=hospitalSetQueryVo.getHosname(),HospitalSet::getHosname,hospitalSetQueryVo.getHosname());
        lqw.eq(null!=hospitalSetQueryVo.getHoscode(),HospitalSet::getHoscode,hospitalSetQueryVo.getHoscode());
//        QueryWrapper<HospitalSet> qw = new QueryWrapper<>();
//        qw.like(!StringUtils.isEmpty(hospitalSetQueryVo.getHosname()), "hosname",hospitalSetQueryVo.getHosname());
//        qw.eq(!StringUtils.isEmpty(hospitalSetQueryVo.getHoscode()), "hoscode", hospitalSetQueryVo.getHoscode());
//        Page<HospitalSet> page = hospitalSetService.page(hospitalSetPage, qw);
        Page<HospitalSet> page = hospitalSetService.page(hospitalSetPage, lqw);
        if(page.getTotal()==0){
            return Result.fail("未查询到数据！");
        }
        return Result.ok(page);
    }

    @PostMapping("/saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){
        return hospitalSetService.saveSet(hospitalSet);
    }

    //根据id获取医院设置
    @GetMapping("/getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id){
        return Result.ok(hospitalSetService.getById(id));
    }

    //修改医院设置
    @PostMapping("/updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if(!flag){
            return Result.fail("修改失败！");
        }
        return Result.ok("修改成功！");
    }

/*    @RequestMapping("/getHospSet")
    public Result getHospSet(@RequestBody Map map){

        return null;
    }*/

    //批量删除医院设置
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList){
        hospitalSetService.removeByIds(idList);
        return Result.ok("删除成功！");
    }
    //医院设置锁定和解锁
    @PutMapping("/lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,@PathVariable Long status){
        boolean flag = hospitalSetService.update(
                new LambdaUpdateWrapper<HospitalSet>()
                        .set(HospitalSet::getStatus, status)
                        .eq(HospitalSet::getId, id));
        if(!flag){
            return Result.fail();
        }
        return Result.ok();
    }

    //发送签名密钥
    @PostMapping("/sendKey/{id}")
    public Result sendKey(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //TODO 发送短信
        return null;
    }
}
