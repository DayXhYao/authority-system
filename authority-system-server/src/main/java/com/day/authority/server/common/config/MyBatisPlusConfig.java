package com.day.authority.server.common.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:25
 */
@MapperScan("com.day.authority.server.model.*.infrastructure.database.mysql.mapper.**")
@Configuration
public class MyBatisPlusConfig {


    /**
     * 分页插件
     * @return 返回分页拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

}
