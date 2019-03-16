package com.jlpay.common.testmq.services;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @author Shaofeng Li
 * @Description: todo
 * @date 2019/3/16 16:58
 * @Version 1.0
 */
@Component
public class RedisKeyExpirationListener  extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {

        byte[] body = message.getBody();
        byte[] channel = message.getChannel();


        System.out.println("body >> " +JSON.toJSONString(new String(body)));
        System.out.println("channel >> " +JSON.toJSONString(new String(channel, Charset.forName("UTF-8"))));



        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        System.out.println("expiredKey >> " +expiredKey);

        if(expiredKey.startsWith("Order:")){
            //如果是Order:开头的key，进行处理
            System.out.println("如果是Order:开头的key，进行处理");
        }
    }
}
