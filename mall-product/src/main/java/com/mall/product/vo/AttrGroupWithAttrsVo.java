package com.mall.product.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.mall.product.entity.AttrEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @auther zhz
 * @Date 2020-11-20 16:41
 */
@Data
public class AttrGroupWithAttrsVo {

    @ApiModelProperty(value = "分组id")
    @TableId
    private Long attrGroupId;

    @ApiModelProperty(value = "组名")
    private String attrGroupName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "描述")
    private String descript;

    @ApiModelProperty(value = "组图标")
    private String icon;

    @ApiModelProperty(value = "所属分类id")
    private Long catelogId;

    @ApiModelProperty(value = "属性分组下的属性")
    private List<AttrEntity> attrs;


}
