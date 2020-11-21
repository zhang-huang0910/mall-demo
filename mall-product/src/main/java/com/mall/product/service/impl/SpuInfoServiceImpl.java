package com.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.Feign.coupon.CouponClient;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;
import com.mall.product.dao.SpuInfoDao;
import com.mall.product.entity.*;
import com.mall.product.service.*;
import com.mall.product.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {
    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private SpuImagesService spuImagesService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private CouponClient couponClient;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void spuSaveInfo(SpuSaveVo spuSaveVo) {
        //1.保存spu的基本属性 pms_spu_info
        SpuInfoEntity infoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuSaveVo, infoEntity);
        infoEntity.setCreateTime(new Date());
        infoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(infoEntity);
        //2.保存spu的描述图片 pms_spu_info_desc
        List<String> decript = spuSaveVo.getDecript();
        if (decript != null && decript.size() > 0) {
            SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
            String descripts = String.join(",", decript);
            descEntity.setSpuId(infoEntity.getId());
            descEntity.setDecript(descripts);
            spuInfoDescService.saveSpuInfoDesc(descEntity);
        }
        //3.保存spu的图片集 pms_spu_images
        spuImagesService.saveImages(infoEntity.getId(), spuSaveVo.getImages());
        //4.保存spu的规格参数 pms_product_attr_value
        List<BaseAttrs> baseAttrs = spuSaveVo.getBaseAttrs();
        List<ProductAttrValueEntity> collect = baseAttrs.stream()
                .map(attr -> {
                    ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
                    valueEntity.setAttrId(attr.getAttrId());
                    AttrEntity attrEntity = attrService.getById(attr.getAttrId());
                    valueEntity.setAttrName(attrEntity.getAttrName());
                    valueEntity.setAttrValue(attr.getAttrValues());
                    valueEntity.setQuickShow(attr.getShowDesc());
                    valueEntity.setSpuId(infoEntity.getId());
                    return valueEntity;
                })
                .collect(Collectors.toList());
        productAttrValueService.saveBatch(collect);
        //5.保存当前spu对应的所有sku信息
        //5.1sku的基本信息 pms_sku_info
        List<Skus> skus = spuSaveVo.getSkus();
        if (skus != null && skus.size() > 0) {
            skus.forEach(e1 -> {
                String defaultImage = "";
                for (Images image : e1.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        defaultImage = image.getImages();
                    }
                }
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(e1, skuInfoEntity);
                skuInfoEntity.setBrandId(infoEntity.getBrandId());
                skuInfoEntity.setCatalogId(infoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(infoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImage);
                skuInfoService.saveSkuInfo(skuInfoEntity);
                Long skuId = skuInfoEntity.getSkuId();
                //5.2 sku的图片信息 pms_sku_images
                List<SkuImagesEntity> skuImagesEntities = e1.getImages().stream()
                        .map(img -> {
                            SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                            skuImagesEntity.setDefaultImg(img.getDefaultImg());
                            skuImagesEntity.setSkuId(skuId);
                            skuImagesEntity.setImgUrl(img.getImages());
                            return skuImagesEntity;
                        })
                        .collect(Collectors.toList());
                skuImagesService.saveBatch(skuImagesEntities);
                //5.3 sku的销售属性 pms_sku_sale_attr_value
                List<Attr> attr = e1.getAttr();
                if (attr != null && attr.size() > 0) {
                    List<SkuSaleAttrValueEntity> collect1 = attr.stream()
                            .map(saleAttr -> {
                                SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                                BeanUtils.copyProperties(saleAttr, skuSaleAttrValueEntity);
                                skuSaleAttrValueEntity.setSkuId(skuId);
                                return skuSaleAttrValueEntity;
                            }).collect(Collectors.toList());
                    skuSaleAttrValueService.saveBatch(collect1);
                }

                //5.4 sku的优惠。满减等信息；mall_sms->sms_sku_ladder sms_spu_bounds sms_sku_full_reduction
            });

        }

    }

    @Override
    public void saveBaseSpuInfo(SpuInfoEntity infoEntity) {
        this.baseMapper.insert(infoEntity);
    }


}