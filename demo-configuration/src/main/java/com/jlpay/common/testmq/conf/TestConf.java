package com.jlpay.common.testmq.conf;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Shaofeng Li
 * @Description: todo
 * @date 2019/3/13 20:44
 * @Version 1.0
 */
@Configuration
@Component
@ConfigurationProperties(prefix = "jlpay")
@Data
public class TestConf {
    private  Notify notify = new Notify();
    private  String node;

    public static TestConf TestConfStatic ;
    {
        TestConfStatic =this;
    }
    @Data
    public   class   Notify {
        String risk;
        String  trans;
    }


}
