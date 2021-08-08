package com.mall.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther zhz
 * @Date 2020-12-05 12:18
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MallAuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAuthServerApplication.class, args);
    }
}
