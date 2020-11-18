package com.mall.search;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther zhz
 * @Date 2020-11-10 13:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallSearchApplication.class)
@Slf4j
public class TestDemo {
    @Autowired
    @Qualifier("esRestClien")
    private RestHighLevelClient client;

    @Test
    public void test1(){
        System.out.println(client);

    }
}
