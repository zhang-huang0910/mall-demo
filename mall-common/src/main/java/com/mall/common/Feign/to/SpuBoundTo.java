package com.mall.common.Feign.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @auther zhz
 * @Date 2020-11-21 20:18
 */
@Data
public class SpuBoundTo {
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
    private Long spuId;
}
