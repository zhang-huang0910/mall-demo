package com.mall.common.Feign.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @auther zhz
 * @Date 2020-11-21 20:32
 */
@Data
public class SkuReductionTo {

    private Long skuId;
    private Integer fullCount;
    private BigDecimal discount;
    private Integer countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer priceStatus;
    private List<MemberPrice> memberPrice;

}
