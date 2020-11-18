package com.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品三级分类
 *
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-11-05 21:24:58
 */
@Data
@TableName("pms_category")
@ApiModel(value = "pms_category", description = "商品三级分类")
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类id")
    @TableId
    private Long catId;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "父分类id")
    private Long parentCid;

    @ApiModelProperty(value = "层级")
    private Integer catLevel;

    @ApiModelProperty(value = "是否显示[0-不显示，1显示]")
    @TableLogic(value = "1",delval = "0")
    private Integer showStatus;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "图标地址")
    private String icon;

    @ApiModelProperty(value = "计量单位")
    private String productUnit;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty(value = "子结构")
    @TableField(exist = false)
    private List<CategoryEntity> children;

}
