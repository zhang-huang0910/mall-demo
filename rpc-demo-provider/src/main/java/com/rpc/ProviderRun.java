package com.rpc;

import com.mall.common.api.service.PersonService;
import com.rpc.provider.PersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @auther zhz
 * @Date 2021-10-06 17:43
 */
@Slf4j
public class ProviderRun {
    public static void main(String[] args) {
        try {
            PersonService personService = new PersonServiceImpl();

            //创建port
            LocateRegistry.createRegistry(6314);

            String url = "rmi://127.0.0.1:6314/personService";
            //绑定
            Naming.bind(url, personService);
            log.info("RMI start success!");
            //创建zookeeper并发布
            ZooKeeper zooServer = new ZooKeeper("hadoop000:2181", 100000, e1 ->
                    log.info("connect zookeeper!")
            );
            zooServer.create("/rpc/provider", url.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            log.info("registry success!");
        } catch (Exception e) {
            log.error("providerRun error!", e);
        }
    }
}
