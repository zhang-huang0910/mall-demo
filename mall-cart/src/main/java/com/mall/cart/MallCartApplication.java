package com.mall.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther zhz
 * @Date 2020-12-05 21:13
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MallCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallCartApplication.class, args);
    }
}
