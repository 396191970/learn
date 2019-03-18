package com.alibaba.csp.sentinel.dashboard.rule.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public  class ZkConfigUtil {

    public static final String GROUP_ID = "SENTINEL_GROUP";

    @Value("${sentinel.remote_address}")
    public   String remoteAddress;

    public  static String REMOTE_ADDRESS ;

    public static final String FLOW_DATA_ID_POSTFIX = "-flow-rules";
    public static final String PARAM_FLOW_POSTFIX = "-param-rules";
    public static final String DEGRADE_POSTFIX = "-degrade-rules";
    public static final String SYSTEM_POSTFIX = "-system-rules";
    public static final String AUTHORITY_POSTFIX = "-authority-rules";
    public static final String SERVER_NAMESPACE_SET_POSTFIX = "-cs-namespace-set";
    public static final String CLIENT_CONFIG_POSTFIX = "-cc-config";
    public static final String CLUSTER_CLIENT_CONFIG ="-cluster-client-config";
    public static final String CLUSTER_MAP_POSTFIX = "-cluster-map";


    public static final int RETRY_TIMES = 3;

    public static final int SLEEP_TIME = 1000;

    @PostConstruct
    private  void init()
    {
        REMOTE_ADDRESS = remoteAddress;
        log.info("REMOTE_ADDRESS:"+REMOTE_ADDRESS);
    }

    public static void setData(String dataId, String rule) throws Exception {
        CuratorFramework zkClient = CuratorFrameworkFactory.newClient(ZkConfigUtil.REMOTE_ADDRESS, new ExponentialBackoffRetry(ZkConfigUtil.SLEEP_TIME, ZkConfigUtil.RETRY_TIMES));
        zkClient.start();
        String path = getPath(ZkConfigUtil.GROUP_ID, dataId);
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat == null) {
            zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, null);
        }
        zkClient.setData().forPath(path, rule.getBytes());

        zkClient.close();
    }


    private static String getPath(String groupId, String dataId) {
        String path = "";
        if (groupId.startsWith("/")) {
            path += groupId;
        } else {
            path += "/" + groupId;
        }
        if (dataId.startsWith("/")) {
            path += dataId;
        } else {
            path += "/" + dataId;
        }
        return path;
    }
}