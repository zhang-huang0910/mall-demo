package com.mall.product.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @auther zhz
 * @Date 2020-11-20 16:04
 */
@Data
public class BrandVo {
    @ApiModelProperty(value = "品牌id")
    @TableId
    private Long brandId;

    @ApiModelProperty(value = "品牌名")
    private String brandName;

}
