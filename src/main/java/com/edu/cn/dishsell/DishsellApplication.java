package com.edu.cn.dishsell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.edu.cn.dishsell.mapper")
//@EnableCaching
public class DishsellApplication {

    public static void main(String[] args) {
        SpringApplication.run(DishsellApplication.class, args);
    }

}
