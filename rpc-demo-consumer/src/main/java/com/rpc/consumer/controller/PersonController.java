package com.rpc.consumer.controller;

import com.mall.common.rpc.pojo.Person;
import com.rpc.consumer.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther zhz
 * @Date 2021-10-06 18:08
 */
@RestController
public class PersonController {
    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/person")
    public List<Person> findAllPerson() {
        return personService.findAll();
    }
}
