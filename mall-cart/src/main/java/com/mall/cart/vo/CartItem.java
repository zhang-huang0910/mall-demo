package com.mall.cart.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @auther zhz
 * @Date 2020-12-05 21:52
 */

public class CartItem {
    private Long skuId;
    private Boolean check = true;
    private String title;
    private List<String> skuAttr;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalPrice;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSkuAttr() {
        return skuAttr;
    }

    public void setSkuAttr(List<String> skuAttr) {
        this.skuAttr = skuAttr;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 计算总价
     *
     * @return
     */
    public BigDecimal getTotalPrice() {
        return this.price.multiply(new BigDecimal("" + this.getCount()));
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
