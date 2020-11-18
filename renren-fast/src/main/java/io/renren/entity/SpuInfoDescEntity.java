package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * spu信息介绍
 * 
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-11-05 21:24:58
 */
@Data
@TableName("pms_spu_info_desc")
@ApiModel(value = "pms_spu_info_desc", description = "spu信息介绍")
public class SpuInfoDescEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "商品id")
	@TableId
	private Long spuId;

	@ApiModelProperty(value = "商品介绍")
	private String decript;


}
