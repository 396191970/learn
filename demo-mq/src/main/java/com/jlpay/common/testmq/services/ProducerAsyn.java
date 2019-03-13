package com.jlpay.common.testmq.services;

import com.jlpay.common.testmq.constant.QueueConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.Scanner;

/**
 * 发送异步消息，并创建临时队列接受异步返回
 *
 * @author Shaofeng Li
 */
@Component
public class ProducerAsyn implements MessageListener {

    @Autowired
    ConnectionFactory connectionFactory;
    private Connection connection;

    private void openConnection() throws JMSException {

        connection = connectionFactory.createConnection();
        connection.start();
    }


    public void sendMessage(String msgInfo) throws JMSException {
        openConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QueueConstant.TEST_PRODUCER_ASYN);
        Queue responseQueue = session.createTemporaryQueue();
        MessageProducer sender = session.createProducer(queue);

        TextMessage msg = session.createTextMessage();
//        msg.setJMSCorrelationID("123-123456");
//        msg.setIntProperty("AccountID", 123);
        msg.setJMSReplyTo(responseQueue);   //设置回复队列
        msg.setText(msgInfo);
        sender.send(msg);
        System.out.println("消息发送 : JMSMessage" + msg.getJMSMessageID());

        //接收回复信息
        System.out.println("等待客户端回复队列：" + msg.getJMSReplyTo());
        String filter = "JMSCorrelationID='" + msg.getJMSMessageID() + "'";
        MessageConsumer reply = session.createConsumer(responseQueue, filter);


        //同步方式等待接收回复
//        TextMessage resMsg = (TextMessage) reply.receive(60 * 1000);
//        if(resMsg != null){
//			System.out.println("客户端回复消息 : " + resMsg.getText() + " JMSCorrelation" + resMsg.getJMSCorrelationID());
//        }else{
//        	System.out.println("等待超时！");
//        }


        //异步方式接收回复
        reply.setMessageListener(this);


    }

    @Override
    public void onMessage(Message message) {
        try {
            String textMessage = ((TextMessage) message).getText();
            System.out.println("客户端回复消息 : " + textMessage + " JMSCorrelation" + message.getJMSCorrelationID());
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
        }
    }

    private void disConnection() throws JMSException {
        connection.close();
    }


    public static void main(String[] args) throws JMSException {
        ProducerAsyn ms = new ProducerAsyn();
        Scanner scan = new Scanner(System.in);
        System.out.print("输入信息：");
        ms.sendMessage(scan.next());
        System.out.print("消息发送完毕！");
    }

}
