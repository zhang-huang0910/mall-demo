package com.mall.product.vo;

import lombok.Data;

/**
 * @auther zhz
 * @Date 2020-11-18 04:55
 */
@Data
public class AttrRespVo extends AttrVo {

    private String catelogName;

    private String groupName;

    private Long[] catelogPath;
}
