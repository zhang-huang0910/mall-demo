package com.rpc.provider;

import com.mall.common.api.service.PersonService;
import com.mall.common.rpc.pojo.Person;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther zhz
 * @Date 2021-10-06 17:40
 */
public class PersonServiceImpl extends UnicastRemoteObject implements PersonService {

    public PersonServiceImpl() throws RemoteException {
    }

    @Override
    public List<Person> findAll() throws RemoteException {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(12, "张三"));
        personList.add(new Person(13, "李四"));
        return personList;
    }
}
