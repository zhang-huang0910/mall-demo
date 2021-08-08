package com.mall.common.Feign.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @auther zhz
 * @Date 2020-11-20 22:55
 */
@Data
public class MemberPrice {

    private Long id;

    private String name;

    private BigDecimal price;

}
