package com.jlpay.common.testmq.services;

import com.jlpay.common.testmq.constant.QueueConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * 接收消息，并获取返回队列，返回信息
 *
 * @author Shaofeng Li
 */
@Component
public class ConsumerAsyn {

    @Autowired
    ConnectionFactory connectionFactory;

    @JmsListener(destination = QueueConstant.TEST_PRODUCER_ASYN)
    public void onMessage(Message message) {
        try {
            String textMessage = ((TextMessage) message).getText();
            System.out.println("接收消息 : " + textMessage + "  JMSMessage" + message.getJMSMessageID());
            Thread.sleep(10 * 1000);//模拟业务处理
            Queue responseQueue = (Queue) message.getJMSReplyTo();
            if (responseQueue != null) {
                Session session = connectionFactory.createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
                TextMessage responseMsg = session.createTextMessage();
                responseMsg.setJMSCorrelationID(message.getJMSMessageID());
                responseMsg.setText("This message is reply from client：" + textMessage);
                session.createProducer(responseQueue).send(responseMsg);
                System.out.println("客户端回复队列：" + responseQueue + "  JMSCorrelation" + responseMsg.getJMSCorrelationID());
            } else {
                System.out.println("服务端回复队列为空");
            }

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

}
