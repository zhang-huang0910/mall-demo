package com.mall.ware.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther zhz
 * @Date 2020-11-07 22:28
 */
@Configuration
public class WareConfig {

    //引入分页
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //设置请求的页面大于等于最大页后的操作，true调回到首页，false继续请求 默认false
        paginationInterceptor.setOverflow(true);
        //设置最大单页的限制，默认500条，-1不限制
        paginationInterceptor.setLimit(1000);
        return paginationInterceptor;
    }

}
