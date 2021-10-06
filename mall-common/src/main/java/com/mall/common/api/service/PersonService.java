package com.mall.common.api.service;

import com.mall.common.rpc.pojo.Person;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @auther zhz
 * @Date 2021-10-06 17:29
 */
public interface PersonService extends Remote {

    List<Person> findAll() throws RemoteException;

}
