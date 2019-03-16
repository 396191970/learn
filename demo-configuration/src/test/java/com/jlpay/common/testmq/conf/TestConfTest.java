package com.jlpay.common.testmq.conf;


import com.jlpay.common.testmq.NotifyServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NotifyServiceApplication.class})

public class TestConfTest {

    @Autowired
    public TestConf testConf;

    @Test
    public void test() {
        System.out.println(testConf.TestConfStatic.toString());

        System.out.println(testConf.toString());
    }
}