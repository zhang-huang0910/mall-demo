package com.mall.order.controller;

import com.mall.common.utils.PageUtils;
import com.mall.common.utils.R;
import com.mall.order.entity.OrderEntity;
import com.mall.order.entity.OrderReturnReasonEntity;
import com.mall.order.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * 订单
 *
 * @author zzh
 * @email zzh@gmail.com
 * @date 2020-09-06 19:34:53
 */
@RestController
@RequestMapping("order/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:order:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:order:info")
    public R info(@PathVariable("id") Long id) {
        OrderEntity order = orderService.getById(id);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:order:save")
    public R save(@RequestBody OrderEntity order) {
        orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:order:update")
    public R update(@RequestBody OrderEntity order) {
        orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:order:delete")
    public R delete(@RequestBody Long[] ids) {
        orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @GetMapping("/send")
    public R sendMessage() {
        OrderReturnReasonEntity returnReasonEntity = new OrderReturnReasonEntity();
        returnReasonEntity.setId(12L);
        returnReasonEntity.setName("退回信息");
        returnReasonEntity.setSort(1);
        returnReasonEntity.setStatus(0);
        returnReasonEntity.setCreateTime(new Date());
        for (int i = 0; i < 3; i++) {
            rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", returnReasonEntity);
        }
        return R.ok();
    }

}
