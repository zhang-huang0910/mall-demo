package com.mall;

import com.mall.product.MallProductApplication;
import com.mall.product.entity.BrandEntity;
import com.mall.product.service.BrandService;
import com.mall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallProductApplication.class)
@Slf4j
public class MallProductApplicationTests {
    @Resource
    private BrandService brandService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setDescript("the best mobile phone in the world!");
        brandService.updateById(brandEntity);
        log.info("save success!");
    }

    @Test
    public void getCategoryPath() {
        Long[] catelogPath = categoryService.findCatelogPath(255L);
        System.out.println(Arrays.toString(catelogPath));
    }

    @Test
    public void testRedisTemplate() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("hello", UUID.randomUUID().toString());
    }

}
