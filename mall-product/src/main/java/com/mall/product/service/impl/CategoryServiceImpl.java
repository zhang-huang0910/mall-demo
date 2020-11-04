package com.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;
import com.mall.product.dao.CategoryDao;
import com.mall.product.entity.CategoryEntity;
import com.mall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> list = baseMapper.selectList(null);
        List<CategoryEntity> treeMenu = list.stream()
                .filter(e1 -> Objects.equals(e1.getParentCid(), 0L))
                .map(e2 -> {
                    e2.setChildren(getChildren(e2, list));
                    return e2;
                })
                .sorted(Comparator.comparing(CategoryEntity::getSort, Comparator.nullsLast(Integer::compareTo)))
                .collect(Collectors.toList());
        return treeMenu;
    }

    private List<CategoryEntity> getChildren(CategoryEntity entity, List<CategoryEntity> all) {
        return all.stream()
                .filter(e3 -> Objects.equals(e3.getParentCid(), entity.getCatId()))
                .map(e4 -> {
                    e4.setChildren(getChildren(e4, all));
                    return e4;
                })
                .sorted(Comparator.comparing(CategoryEntity::getSort, Comparator.nullsLast(Integer::compareTo)))
                .collect(Collectors.toList());
    }

}