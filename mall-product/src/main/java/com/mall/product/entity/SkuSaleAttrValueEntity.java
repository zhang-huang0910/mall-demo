package com.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * sku销售属性&值
 * 
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-11-05 21:24:58
 */
@Data
@TableName("pms_sku_sale_attr_value")
@ApiModel(value = "pms_sku_sale_attr_value", description = "sku销售属性&值")
public class SkuSaleAttrValueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	@TableId
	private Long id;

	@ApiModelProperty(value = "sku_id")
	private Long skuId;

	@ApiModelProperty(value = "attr_id")
	private Long attrId;

	@ApiModelProperty(value = "销售属性名")
	private String attrName;

	@ApiModelProperty(value = "销售属性值")
	private String attrValue;

	@ApiModelProperty(value = "顺序")
	private Integer attrSort;


}
