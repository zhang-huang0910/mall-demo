package com.mall.coupon.dao;

import com.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-09-06 19:20:01
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
