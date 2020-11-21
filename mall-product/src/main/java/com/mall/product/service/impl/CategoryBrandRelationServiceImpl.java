package com.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;
import com.mall.product.dao.BrandDao;
import com.mall.product.dao.CategoryBrandRelationDao;
import com.mall.product.entity.CategoryBrandRelationEntity;
import com.mall.product.service.CategoryBrandRelationService;
import com.mall.product.vo.BrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Autowired
    private BrandDao brandDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<BrandVo> getBrandsByCatId(Long catelogId) {
        if (catelogId == null) {
            return null;
        }
        List<Long> brandIds = categoryBrandRelationDao.selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catelogId)).stream()
                .map(CategoryBrandRelationEntity::getBrandId)
                .collect(Collectors.toList());
        if (brandIds != null && brandIds.size() > 0) {
            return brandDao.selectBatchIds(brandIds).stream()
                    .map(e1 -> {
                        BrandVo brandVo = new BrandVo();
                        brandVo.setBrandId(e1.getBrandId());
                        brandVo.setBrandName(e1.getName());
                        return brandVo;
                    })
                    .collect(Collectors.toList());


        }
        return null;
    }

}