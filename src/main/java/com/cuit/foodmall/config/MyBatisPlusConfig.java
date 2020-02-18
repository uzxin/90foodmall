package com.cuit.foodmall.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: YX
 * @date: 2020/2/17 15:15
 * @description: mybatisPlus相关配置
 */
@Configuration
@MapperScan("com.cuit.foodmall.mapper")
public class MyBatisPlusConfig {

    /**
     * 功能描述: 分页插件
     * @author : YX
     * @date : 2020/2/17 15:15
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 3.2版本下需要配置，逻辑删除
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

}

