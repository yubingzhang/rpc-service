package com.rpc.registry;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceRegistry {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String ZK_REGISTRY_PATH = "/rpc";

    @Value("${register.address}")
    private String registryAddress;

    public void register(String data){
        if (data != null){
            ZkClient zkClient = connectServer();
            if (zkClient != null){
                addRootNode(zkClient);
                createNode(zkClient,data);
            }
        }
    }

    //连接zk
    private ZkClient connectServer(){
        ZkClient zkClient = new ZkClient(registryAddress,20000,20000);
        return zkClient;
    }

    /**
     * 创建根节点
     * @param zkClient
     */
    private void addRootNode(ZkClient zkClient){
        boolean exists = zkClient.exists(ZK_REGISTRY_PATH);
        if (!exists){
            zkClient.createPersistent(ZK_REGISTRY_PATH);
            logger.info("创建zk根节点：{}",ZK_REGISTRY_PATH);
        }
    }

    /**
     * 创建临时顺序节点，提供者url
     * @param zkClient
     * @param data
     */
    private void createNode(ZkClient zkClient,String data){
        String path = zkClient.create(ZK_REGISTRY_PATH+"/provider",data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        logger.info("创建zookeeper数据节点 ({} => {})", path, data);
    }

}
