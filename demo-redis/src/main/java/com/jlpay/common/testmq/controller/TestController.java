package com.jlpay.common.testmq.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Shaofeng Li
 */
@RestController
@Data
public class TestController {

    @Autowired
    RedisTemplate redisTemplate;


    @RequestMapping("set")
    public String set()  {

        redisTemplate.opsForValue().set("key","value",2, TimeUnit.SECONDS);
        System.out.print("成功");

        return "成功";
    }
    @RequestMapping("get")
    public String get()  {


        System.out.print(redisTemplate.opsForValue().get("key")+"成功");

        return "成功";
    }

}
