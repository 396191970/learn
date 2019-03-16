package com.jlpay.common.testmq.services;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

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
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        if(expiredKey.startsWith("Order:")){
            //如果是Order:开头的key，进行处理
        }
    }
}
