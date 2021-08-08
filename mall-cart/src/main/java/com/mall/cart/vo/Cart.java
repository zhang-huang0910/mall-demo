package com.mall.cart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @auther zhz
 * @Date 2020-12-05 21:52
 */
public class Cart {

    @ApiModelProperty("购物项")
    private List<CartItem> items;

    @ApiModelProperty("商品数量")
    private Integer countNum;

    @ApiModelProperty("商品类型数量")
    private Integer countType;

    @ApiModelProperty("商品总价")
    private BigDecimal totalAmount;

    @ApiModelProperty("减免价格")
    private BigDecimal reduce;

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Integer getCountNum() {
        int count = 0;
        if (this.items != null && this.items.size() > 0) {
            for (CartItem item : items) {
                count += item.getCount();
            }
        }
        return count;
    }

    public Integer getCountType() {
        int count = 0;
        if (this.items != null && this.items.size() > 0) {
            count = items.size();
        }
        return count;
    }


    public BigDecimal getTotalAmount() {
        BigDecimal amount = new BigDecimal(0);
        //增加的
        if (this.items != null && this.items.size() > 0) {
            for (CartItem item : items) {
                amount = amount.add(item.getTotalPrice());
            }
        }
        //优惠满减
        amount = amount.subtract(this.getReduce());
        return amount;
    }


    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }
}
