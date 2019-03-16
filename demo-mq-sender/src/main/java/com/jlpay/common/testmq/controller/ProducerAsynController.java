package com.jlpay.common.testmq.controller;

import com.jlpay.common.testmq.services.ProducerAsyn;
import com.jlpay.common.testmq.services.StopJmsListener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

/**
 * @author Shaofeng Li
 */
@RestController
@Data
public class ProducerAsynController {

    @Autowired
    ProducerAsyn producerAsyn;
    @Autowired
    StopJmsListener stopJmsListener;

    @RequestMapping("ProducerAsyn")
    public String ProducerAsyn() throws JMSException {

        producerAsyn.sendMessage("输入信息：");

        System.out.print("消息发送完毕！");

        return "成功";
    }

    @RequestMapping("stop")
    public String stop() throws JMSException {

        stopJmsListener.stop();

        System.out.print("stop");

        return "stop";
    }
}
