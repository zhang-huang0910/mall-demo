package com.mall;

import com.mall.product.MallProductApplication;
import com.mall.product.entity.BrandEntity;
import com.mall.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallProductApplication.class)
@Slf4j
public class MallProductApplicationTests {
    @Resource
    private BrandService brandService;
    @Test
    public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setDescript("the best mobile phone in the world!");
        brandService.updateById(brandEntity);
        log.info("save success!");
    }

}
