package com.jlpay.common.testmq.controller;

import com.jlpay.common.testmq.services.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Shaofeng Li
 */
@RestController
@Data
public class ProducerController {


    @Autowired
    Producer producer;

    @RequestMapping("/producer")
    public String producer() {

        producer.send(Producer.DEFAULT_QUEUE, "输入信息：");
        System.out.println("producer");
        return "成功";
    }
}
