package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * sku信息
 * 
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-11-05 21:24:58
 */
@Data
@TableName("pms_sku_info")
@ApiModel(value = "pms_sku_info", description = "sku信息")
public class SkuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "skuId")
	@TableId
	private Long skuId;

	@ApiModelProperty(value = "spuId")
	private Long spuId;

	@ApiModelProperty(value = "sku名称")
	private String skuName;

	@ApiModelProperty(value = "sku介绍描述")
	private String skuDesc;

	@ApiModelProperty(value = "所属分类id")
	private Long catalogId;

	@ApiModelProperty(value = "品牌id")
	private Long brandId;

	@ApiModelProperty(value = "默认图片")
	private String skuDefaultImg;

	@ApiModelProperty(value = "标题")
	private String skuTitle;

	@ApiModelProperty(value = "副标题")
	private String skuSubtitle;

	@ApiModelProperty(value = "价格")
	private BigDecimal price;

	@ApiModelProperty(value = "销量")
	private Long saleCount;


}
