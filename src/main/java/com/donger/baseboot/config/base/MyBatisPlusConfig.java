package com.donger.baseboot.config.base;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisPlusConfiguration 配置类
 *
 * @author XuYunXuan
 * @date 2019-01-08 19:21
 */
@Configuration
@MapperScan(basePackages = {"com.donger.baseboot.modules.*.mapper"})
public class MyBatisPlusConfig {

    /**
     * 开启逻辑删除
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}