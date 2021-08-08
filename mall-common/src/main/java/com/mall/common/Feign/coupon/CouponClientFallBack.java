package com.mall.common.Feign.coupon;

import com.mall.common.Feign.to.SkuReductionTo;
import com.mall.common.Feign.to.SpuBoundTo;
import com.mall.common.utils.R;
import org.springframework.stereotype.Component;

/**
 * @auther zhz
 * @Date 2020-11-21 18:14
 */
@Component
public class CouponClientFallBack implements CouponClient {
    @Override
    public R saveSpuBounds(SpuBoundTo spuBoundTo) {
        return R.error("发生了点错误，请稍后再试！");
    }

    @Override
    public R saveSkuReductionTo(SkuReductionTo skuReductionTo) {
        return R.error("发生了点错误，请稍后再试！");
    }
}
