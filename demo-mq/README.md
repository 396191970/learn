# 功能
# MQ 双向队列 request-response模式
## 简述


request-response的通信方式很常见，但是不是默认提供的一种模式。在前面的两种模式中都是一方负责发送消息而另外一方负责处理。而我们实际中的很多应用相当于一种一应一答的过程，需要双方都能给对方发送消息。于是请求-应答的这种通信方式也很重要。它也应用的很普遍。



请求-应答方式并不是JMS规范系统默认提供的一种通信方式，而是通过在现有通信方式的基础上稍微运用一点技巧实现的。下图是典型的请求-应答方式的交互过程：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190316204259356.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE1Mjg4MDIz,size_16,color_FFFFFF,t_70)



在JMS里面，如果要实现请求/应答的方式，可以利用JMSReplyTo和JMSCorrelationID消息头来将通信的双方关联起来。

## 发送端
 步骤如下：

 - 创建连接
 - 创建session
 -  创建队列
  -   创建临时队列
  -   创建发送者，绑定发送队列
   -  创建发送消息，绑定临时队列
  -   发送消息
  -   创建临时队列接受者，绑定消息监听
   -  异步消息监听处理回调消息

 ### 代码
```
package com.jlpay.common.testmq.services;

import com.jlpay.common.testmq.constant.QueueConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.Scanner;

/**
 * 发送异步消息，并创建临时队列接收异步返回
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

```



## 接收端
 步骤如下：
 - 创建连接
 - 创建session
 - 创建队列
 - 创建队列接受者，绑定消息监听

 - 异步消息监听处理回调消息

 - 业务处理

 - 从队列消息中获取临时队列

 - 创建临时发送者

 - 发送回复消息

###  代码

```
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
```
完整代码
 github
https://github.com/396191970/learn/tree/master/demo-mq


## 关闭消息监听
以便应用停止时调用，避免无法关闭应用，和消息处理一半导致丢失问题 见 StopJmsListener
```$xslt

    /**
     * 关闭JMS监听，不再接受消息
     */
    public void stop()
    {
        Collection<MessageListenerContainer> listenerContainers = jmsListenerEndpointRegistry.getListenerContainers();
        for (Iterator<MessageListenerContainer> iterator = listenerContainers.iterator(); iterator.hasNext(); ) {
            MessageListenerContainer next =  iterator.next();
            next.stop();
        }
    }
```


# 测试
测试见controller里面的测试方法
- http://127.0.0.1:43343/swagger-ui.html#/