package com.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.Query;
import com.mall.product.dao.AttrAttrgroupRelationDao;
import com.mall.product.dao.AttrDao;
import com.mall.product.dao.AttrGroupDao;
import com.mall.product.entity.AttrAttrgroupRelationEntity;
import com.mall.product.entity.AttrEntity;
import com.mall.product.entity.AttrGroupEntity;
import com.mall.product.service.AttrGroupService;
import com.mall.product.vo.AttrGroupRelaionVo;
import com.mall.product.vo.AttrGroupWithAttrsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {
    @Autowired
    private AttrDao attrDao;

    @Autowired
    private AttrGroupService groupService;

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrGroupDao groupDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer categoryId) {
        if (categoryId == null || categoryId == 0) {
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params));
            return new PageUtils(page);
        } else {
            String key = (String) params.get("key");
            QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>().eq("catelog_id", categoryId);
            if (StringUtils.isNotBlank(key)) {
                wrapper.and((obj) ->
                        obj.eq("attr_group_id", key).or().like("attr_group_name", key))
                ;
            }
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);
            return new PageUtils(page);
        }

    }

    @Override
    public List<AttrEntity> getAttrById(Long attrGroupId) {
        List<Long> attrIds = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrGroupId)).stream()
                .map(e1 -> e1.getAttrId())
                .collect(Collectors.toList());
        if (attrIds != null && attrIds.size() > 0) {
            List<AttrEntity> attrEntities = attrDao.selectBatchIds(attrIds);
            return attrEntities;
        }
        return null;

    }

    @Override
    public void deleteRelation(List<AttrGroupRelaionVo> relaionVos) {
        relationDao.deleteBatchRelationIds(relaionVos);

    }

    @Override
    public PageUtils getNoRelationAttr(Map<String, Object> params, Long attrGroupId) {
        //查找分类id
        AttrGroupEntity attrGroupEntity = groupDao.selectById(attrGroupId);
        if (attrGroupEntity != null) {
            Long catelogId = attrGroupEntity.getCatelogId();
            //查找此类下的分组属性
            List<Long> attrGoupIds = groupDao.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId)).stream()
                    .map(AttrGroupEntity::getAttrGroupId)
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(attrGoupIds)) {
                return null;
            }
            List<Long> attrIds = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", attrGoupIds)).stream()
                    .map(AttrAttrgroupRelationEntity::getAttrId)
                    .collect(Collectors.toList());
            //查找此类下的未被选择属性
            String key = (String) params.get("key");
            QueryWrapper<AttrEntity> query = new QueryWrapper<>();
            query.eq("catelog_id", catelogId);
            if (attrIds != null && attrIds.size() > 0) {
                query.notIn("attr_id", attrIds);
            }
            if (StringUtils.isNotBlank(key)) {
                query.eq("attr_id", key).or().like("attr_name", key);
            }
            IPage<AttrEntity> page = attrDao.selectPage(new Query<AttrEntity>().getPage(params), query);
            return new PageUtils(page);
        }
        return null;
    }

    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        if (catelogId != null) {
            List<AttrGroupWithAttrsVo> attrGroupWithAttrsVos = groupDao.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId)).stream()
                    .map(e1 -> {
                        AttrGroupWithAttrsVo attrsVo = new AttrGroupWithAttrsVo();
                        BeanUtils.copyProperties(e1, attrsVo);
                        attrsVo.setAttrs(groupService.getAttrById(e1.getAttrGroupId()));
                        return attrsVo;
                    })
                    .filter(e1 -> Objects.nonNull(e1.getAttrs()) && (e1.getAttrs().size() > 0))
                    .collect(Collectors.toList());
            return attrGroupWithAttrsVos;
        }
        return null;
    }

}