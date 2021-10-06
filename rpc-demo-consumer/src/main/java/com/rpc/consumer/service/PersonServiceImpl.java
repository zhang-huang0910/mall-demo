package com.rpc.consumer.service;

import com.mall.common.api.service.PersonService;
import com.mall.common.rpc.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Service;

import java.rmi.Naming;
import java.util.List;

/**
 * @auther zhz
 * @Date 2021-10-06 18:08
 */
@Service
@Slf4j
public class PersonServiceImpl {

    public List<Person> findAll() {
        //创建zookeeper并发布
        try {
            ZooKeeper zooServer = new ZooKeeper("hadoop000:2181", 100000, e1 ->
                    log.info("connect zookeeper!")
            );
            byte[] data = zooServer.getData("/rpc/provider", null, null);
            PersonService personService = (PersonService) Naming.lookup(new String(data));
            List<Person> personList = personService.findAll();
            return personList;
        } catch (Exception e) {

        }

        return null;
    }
}
