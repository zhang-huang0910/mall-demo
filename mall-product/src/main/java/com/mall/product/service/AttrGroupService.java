package com.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.utils.PageUtils;
import com.mall.product.entity.AttrEntity;
import com.mall.product.entity.AttrGroupEntity;
import com.mall.product.vo.AttrGroupRelaionVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-09-06 17:58:41
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Integer categoryId);

    List<AttrEntity>  getAttrById(Long attrGroupId);

    void deleteRelation(List<AttrGroupRelaionVo> relaionVos);
}

