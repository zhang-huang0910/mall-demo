package com.mall.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther zhz
 * @Date 2020-12-05 22:47
 */
@Controller
public class CartController {

    @GetMapping("/cat.html")
    public String cartListPage(@RequestParam(value = "status", required = false) Integer status) {
        //登录成功
        if (status == 1) {

        } else {

        }
        return "cartList";
    }
}
