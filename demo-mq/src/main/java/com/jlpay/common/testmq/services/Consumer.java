package com.jlpay.common.testmq.services;

import com.jlpay.common.testmq.constant.QueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author Shaofeng Li
 */
@Component
@Slf4j
public class Consumer {


    @Autowired
    Producer producer;

    @JmsListener(destination = QueueConstant.TEST_PRODUCER)
    public void receiveQueue() {

        System.out.println("receiveQueue");

    }
}
