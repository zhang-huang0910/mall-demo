package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.utils.PageUtils;
import com.mall.product.entity.PmsAttrValueEntity;

import java.util.Map;

/**
 * spu属性值
 *
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-09-06 14:03:49
 */
public interface PmsAttrValueService extends IService<PmsAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

