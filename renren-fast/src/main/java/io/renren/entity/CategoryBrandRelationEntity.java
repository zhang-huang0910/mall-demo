package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 品牌分类关联
 * 
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-11-05 21:24:58
 */
@Data
@TableName("pms_category_brand_relation")
@ApiModel(value = "pms_category_brand_relation", description = "品牌分类关联")
public class CategoryBrandRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "品牌分类关联id")
	@TableId
	private Long id;

	@ApiModelProperty(value = "品牌id")
	private Long brandId;

	@ApiModelProperty(value = "分类id")
	private Long catelogId;

	@ApiModelProperty(value = "品牌名")
	private String brandName;

	@ApiModelProperty(value = "分类名")
	private String catelogName;


}
