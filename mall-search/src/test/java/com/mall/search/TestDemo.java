package com.mall.search;

import com.alibaba.fastjson.JSON;
import com.mall.search.cnfig.SearchConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

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
    public void searchData() throws IOException {
        //1.创建索引请求
        SearchRequest searchRequest = new SearchRequest();
        //2.指定索引
        searchRequest.indices("bank");
        //指定DSL，检索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //构造检索条件
        // sourceBuilder.query()
        // sourceBuilder.from()
        // sourceBuilder.size()
        // sourceBuilder.aggregation()
        sourceBuilder.query(QueryBuilders.matchQuery("address", "mill"));
        //按照年龄的值分布进行聚合
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageAgg").field("age").size(10);
        sourceBuilder.aggregation(aggregationBuilder);
        //聚合
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        sourceBuilder.aggregation(balanceAvg);
        System.out.println(sourceBuilder.toString());
        searchRequest.source(sourceBuilder);
        //执行操作
        SearchResponse search = client.search(searchRequest, SearchConfig.COMMON_OPTIONS);
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            String sourceAsString = documentFields.getSourceAsString();
        }
        System.out.println(search.toString());

    }

    @Test
    public void test1() {
        System.out.println(client);

    }

    /**
     * 存储数据
     */
    @Test
    public void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        indexRequest.type("1");
        //indexRequest.source("userName","zhangsan","age",13,"gender","男");
        String jsonString = JSON.toJSONString(new User("zhangsan", "男", 18));
        indexRequest.source(jsonString, XContentType.JSON);
        IndexResponse index = client.index(indexRequest, SearchConfig.COMMON_OPTIONS);
        //提取响应的数据
        System.out.println(index);

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class User {
        private String userName;
        private String gender;
        private Integer age;
    }
}
