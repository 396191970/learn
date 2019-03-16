#功能
redis 操作demo
设置redis 过期监听处理
# 实现
可以利用redis天然的key自动过期机制，下单时将订单id写入redis，
过期时间30分钟，30分钟后检查订单状态，如果未支付，
则进行处理但是key过期了redis有通知吗？答案是肯定的。

## 开启redis key过期提醒
修改redis相关事件配置。找到redis配置文件redis.conf，
查看“notify-keyspace-events”的配置项，如果没有，
添加“notify-keyspace-events Ex”，如果有值，添加Ex，相关参数说明如下：

```$xslt

K：keyspace事件，事件以__keyspace@<db>__为前缀进行发布；         
E：keyevent事件，事件以__keyevent@<db>__为前缀进行发布；         
g：一般性的，非特定类型的命令，比如del，expire，rename等；        
$：字符串特定命令；         
l：列表特定命令；         
s：集合特定命令；         
h：哈希特定命令；         
z：有序集合特定命令；         
x：过期事件，当某个键过期并删除时会产生该事件；         
e：驱逐事件，当某个键因maxmemore策略而被删除时，产生该事件；         
A：g$lshzxe的别名，因此”AKE”意味着所有事件。
```

## 添加依赖
```$xslt

    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```

## 定义配置RedisListenerConfig
## 定义监听器
有两种方式
- 实现KeyExpirationEventMessageListener接口，查看源码发现，该接口监听所有db的过期事件keyevent@*:expired"
- 或者打开RedisListenerConfig中 container.addMessageListener(new RedisExpiredListener(), new PatternTopic("__keyevent@0__:expired")); 注释，再定义监听器，监控__keyevent@0__:expired事件，即db0过期事件。这个地方定义的比较灵活，可以自己定义监控什么事件。
- 具体见代码

#测试

- redis 设值 会设置过期时间1秒
http://127.0.0.1:50000/set
- redis 获取对象
http://127.0.0.1:50000/get

- 一秒到后listiner会获取到到期通知，进行业务处理