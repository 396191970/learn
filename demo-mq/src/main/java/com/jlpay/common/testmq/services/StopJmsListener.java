package com.jlpay.common.testmq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Shaofeng Li
 * @Description: todo
 * @date 2019/3/16 15:49
 * @Version 1.0
 */
@Component
public class StopJmsListener {

    @Autowired
    JmsListenerEndpointRegistry jmsListenerEndpointRegistry;

    /**
     * 关闭JMS监听，不在接受消息
     */
    public void stop()
    {
        Collection<MessageListenerContainer> listenerContainers = jmsListenerEndpointRegistry.getListenerContainers();
        for (Iterator<MessageListenerContainer> iterator = listenerContainers.iterator(); iterator.hasNext(); ) {
            MessageListenerContainer next =  iterator.next();
            next.stop();
        }
    }
}
