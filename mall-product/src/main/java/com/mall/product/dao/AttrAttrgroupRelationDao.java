package com.mall.product.dao;

import com.mall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.product.vo.AttrGroupRelaionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 * 
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-09-06 17:58:41
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    void deleteBatchRelationIds(@Param("relaionVos") List<AttrGroupRelaionVo> relaionVos);
}
