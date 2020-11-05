package com.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品评价回复关系
 * 
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-11-05 21:24:58
 */
@Data
@TableName("pms_comment_replay")
@ApiModel(value = "pms_comment_replay", description = "商品评价回复关系")
public class CommentReplayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	@TableId
	private Long id;

	@ApiModelProperty(value = "评论id")
	private Long commentId;

	@ApiModelProperty(value = "回复id")
	private Long replyId;


}
