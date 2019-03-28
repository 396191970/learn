package com.jlpay.common.testmq;


import com.jlpay.common.testmq.services.Dispatcher;
import com.jlpay.commons.rpc.thrift.server.RpcAdapterImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


/**
 * 排除RpcAdapterImpl  Dispatcher 重新
 * @author Shaofeng Li
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.jlpay.commons.rpc.thrift.server","com.jlpay.commons.rpc.thrift.referer.zookeeper",
        "com.jlpay.commons.rpc.thrift.referer.service", "com.jlpay.common.testmq"},
excludeFilters =@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {com.jlpay.commons.rpc.thrift.server.Dispatcher.class,
        com.jlpay.commons.rpc.thrift.server.RpcAdapterImpl.class}))
public class TestRpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestRpcApplication.class, args);
    }


}

