package com.mall.product.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.google.common.base.Predicates;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @auther zhz
 * @Date 2020-10-19 03:37
 */
@Configuration
//@EnableSwagger2
//@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@MapperScan("com.mall.product.dao")
@EnableTransactionManagement
public class ProductConfig {


    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mall.product.controller"))
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build().groupName("product");
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("商品中心API文档")
                .description("本文档描述了商品中心微服务接口定义")
                .version("1.0")
                .contact(new Contact("dfzh", "www.google.com", "zh.@gmall.com"))
                .build();
    }

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
