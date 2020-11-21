package com.mall.common.Feign.coupon;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "mall-coupon", fallback = CouponClientFallBack.class)
public interface CouponClient {
    
}
