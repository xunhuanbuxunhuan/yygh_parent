package com.atguigu.yygh.hosp.service.impl;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.utils.MD5;
import com.atguigu.yygh.hosp.mapper.HospitalSetMapper;
import com.atguigu.yygh.hosp.service.HospitalSetService;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Description TODO
 * @Author fengwenhao
 * @Date 2023/5/13 17:20
 */

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    @Override
    public Result saveSet(HospitalSet hospitalSet) {
        //初始化状态，1，启用，0，禁用
        hospitalSet.setStatus(1);
        //生成密钥，并进行md5加密
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+ random.nextInt(1000)));
        boolean flag = save(hospitalSet);
        if(!flag){
            return Result.fail();
        }
        return Result.ok();
    }
}
