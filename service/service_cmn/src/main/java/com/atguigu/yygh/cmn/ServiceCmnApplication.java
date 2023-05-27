package com.atguigu.yygh.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description TODO
 * @Author fengwenhao
 * @Date 2023/5/22 21:52
 */
@SpringBootApplication
@ComponentScan(basePackages ={"com.atguigu"} )
public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class,args);
    }
}
