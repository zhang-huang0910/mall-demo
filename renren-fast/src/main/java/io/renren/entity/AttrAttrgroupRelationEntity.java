package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 属性&属性分组关联
 * 
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-11-05 21:24:58
 */
@Data
@TableName("pms_attr_attrgroup_relation")
@ApiModel(value = "pms_attr_attrgroup_relation", description = "属性&属性分组关联")
public class AttrAttrgroupRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	@TableId
	private Long id;

	@ApiModelProperty(value = "属性id")
	private Long attrId;

	@ApiModelProperty(value = "属性分组id")
	private Long attrGroupId;

	@ApiModelProperty(value = "属性组内排序")
	private Integer attrSort;


}
