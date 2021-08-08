package com.mall.common.Feign.coupon;

import com.mall.common.Feign.to.SkuReductionTo;
import com.mall.common.Feign.to.SpuBoundTo;
import com.mall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "mall-coupon", fallback = CouponClientFallBack.class)
public interface CouponClient {

    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("/coupon/skufullreduction/saveInfo")
    R saveSkuReductionTo(SkuReductionTo skuReductionTo);
}
