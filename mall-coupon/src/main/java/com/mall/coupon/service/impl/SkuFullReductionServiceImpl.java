package com.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.Feign.to.SkuReductionTo;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;
import com.mall.coupon.dao.SkuFullReductionDao;
import com.mall.coupon.entity.MemberPriceEntity;
import com.mall.coupon.entity.SkuFullReductionEntity;
import com.mall.coupon.entity.SkuLadderEntity;
import com.mall.coupon.service.MemberPriceService;
import com.mall.coupon.service.SkuFullReductionService;
import com.mall.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {

    @Autowired
    private SkuLadderService skuLadderService;

    @Autowired
    private MemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuReduction(SkuReductionTo skuReductionTo) {
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setSkuId(skuReductionTo.getSkuId());
        skuLadderEntity.setFullCount(skuReductionTo.getFullCount());
        skuLadderEntity.setDiscount(skuReductionTo.getDiscount());
        skuLadderEntity.setAddOther(skuReductionTo.getCountStatus());
        skuLadderService.save(skuLadderEntity);
        SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(skuReductionTo, skuFullReductionEntity);
        this.save(skuFullReductionEntity);
        //3.member_price
        List<MemberPriceEntity> memberPriceEntities = skuReductionTo.getMemberPrice().stream()
                .map(e1 -> {
                    MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
                    memberPriceEntity.setSkuId(skuReductionTo.getSkuId());
                    memberPriceEntity.setMemberLevelId(e1.getId());
                    memberPriceEntity.setMemberLevelName(e1.getName());
                    memberPriceEntity.setMemberPrice(e1.getPrice());
                    memberPriceEntity.setAddOther(1);
                    return memberPriceEntity;
                })
                .collect(Collectors.toList());
        memberPriceService.saveBatch(memberPriceEntities);
    }

}