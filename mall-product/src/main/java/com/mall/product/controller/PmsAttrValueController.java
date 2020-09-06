package com.mall.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.product.entity.PmsAttrValueEntity;
import com.mall.product.service.PmsAttrValueService;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.R;



/**
 * spu属性值
 *
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-09-06 16:14:43
 */
@RestController
@RequestMapping("product/pmsattrvalue")
public class PmsAttrValueController {
    @Autowired
    private PmsAttrValueService pmsAttrValueService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:pmsattrvalue:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pmsAttrValueService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:pmsattrvalue:info")
    public R info(@PathVariable("id") Long id){
		PmsAttrValueEntity pmsAttrValue = pmsAttrValueService.getById(id);

        return R.ok().put("pmsAttrValue", pmsAttrValue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:pmsattrvalue:save")
    public R save(@RequestBody PmsAttrValueEntity pmsAttrValue){
		pmsAttrValueService.save(pmsAttrValue);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:pmsattrvalue:update")
    public R update(@RequestBody PmsAttrValueEntity pmsAttrValue){
		pmsAttrValueService.updateById(pmsAttrValue);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:pmsattrvalue:delete")
    public R delete(@RequestBody Long[] ids){
		pmsAttrValueService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
