package com.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;
import com.mall.product.dao.SpuImagesDao;
import com.mall.product.entity.SpuImagesEntity;
import com.mall.product.service.SpuImagesService;
import com.mall.product.vo.Images;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuImagesEntity> page = this.page(
                new Query<SpuImagesEntity>().getPage(params),
                new QueryWrapper<SpuImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveImages(Long id, List<Images> images) {
        List<Images> validIamges = images.stream()
                .filter(e1 -> StringUtils.isNotBlank(e1.getImages()))
                .collect(Collectors.toList());
        if (images != null && images.size() > 0) {
            List<SpuImagesEntity> spuImagesEntities = validIamges.stream()
                    .map(e1 -> {
                        SpuImagesEntity imagesEntity = new SpuImagesEntity();
                        imagesEntity.setSpuId(id);
                        imagesEntity.setImgUrl(e1.getImages());
                        imagesEntity.setDefaultImg(e1.getDefaultImg());
                        return imagesEntity;
                    })
                    .collect(Collectors.toList());
            this.saveBatch(spuImagesEntities);
        }
    }

}