package com.mall.common.rpc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @auther zhz
 * @Date 2021-10-06 17:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    private Integer id;
    private String name;
}
