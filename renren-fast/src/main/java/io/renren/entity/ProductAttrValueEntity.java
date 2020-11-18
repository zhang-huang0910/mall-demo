package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * spu属性值
 * 
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-11-05 21:24:58
 */
@Data
@TableName("pms_product_attr_value")
@ApiModel(value = "pms_product_attr_value", description = "spu属性值")
public class ProductAttrValueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	@TableId
	private Long id;

	@ApiModelProperty(value = "商品id")
	private Long spuId;

	@ApiModelProperty(value = "属性id")
	private Long attrId;

	@ApiModelProperty(value = "属性名")
	private String attrName;

	@ApiModelProperty(value = "属性值")
	private String attrValue;

	@ApiModelProperty(value = "顺序")
	private Integer attrSort;

	@ApiModelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】")
	private Integer quickShow;


}
