package com.mall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;

import com.mall.product.dao.PmsAttrValueDao;
import com.mall.product.entity.PmsAttrValueEntity;
import com.mall.product.service.PmsAttrValueService;


@Service("pmsAttrValueService")
public class PmsAttrValueServiceImpl extends ServiceImpl<PmsAttrValueDao, PmsAttrValueEntity> implements PmsAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsAttrValueEntity> page = this.page(
                new Query<PmsAttrValueEntity>().getPage(params),
                new QueryWrapper<PmsAttrValueEntity>()
        );

        return new PageUtils(page);
    }

}