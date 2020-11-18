package com.mall.product.controller;

import com.mall.common.utils.PageUtils;
import com.mall.common.utils.R;
import com.mall.product.entity.AttrEntity;
import com.mall.product.entity.AttrGroupEntity;
import com.mall.product.service.AttrAttrgroupRelationService;
import com.mall.product.service.AttrGroupService;
import com.mall.product.service.CategoryService;
import com.mall.product.vo.AttrGroupRelaionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 属性分组
 *
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-09-06 17:58:41
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrAttrgroupRelationService relationService;

    ///product/attrgroup/1/attr/relation [{attrId: 2, attrGroupId: 1}]

    @GetMapping("/{attrGroupId}/attr/relation")
    public R getAttrById(@PathVariable("attrGroupId") Long attrGroupId) {
        List<AttrEntity> attrVos = attrGroupService.getAttrById(attrGroupId);
        return R.ok().put("data", attrVos);
    }

    // product/attrgroup/attr/relation/delete
    @PostMapping("/attr/relation/delete")
    public R batchDelete(@RequestBody List<AttrGroupRelaionVo> relaionVos) {
        //System.out.println(relaionVos);
        attrGroupService.deleteRelation(relaionVos);
        return R.ok();
    }


    /**
     * 列表
     */
    @GetMapping("/list/{categoryId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable("categoryId") Integer categoryId) {
        //PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrGroupService.queryPage(params, categoryId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        Long[] path = categoryService.findCatelogPath(catelogId);
        attrGroup.setCatelogPath(path);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
