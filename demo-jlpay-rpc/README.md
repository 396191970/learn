# 功能
提供rpc服务，在接收数据的基础上，添加返回码和描述信息放回客户端

## 脚本
启动脚本 配置node port 输入日志文件
```aidl
java -Dthrift.register.node=/auth/auth_server -Dthrift.register.port=55555 -jar demo-jlpay-rpc-0.0.1-SNAPSHOT.jar >>auth_server.log &
java -Dthrift.register.node=/auth/auth_credlink -Dthrift.register.port=55556 -jar demo-jlpay-rpc-0.0.1-SNAPSHOT.jar  >>auth_credlink.log &
java -Dthrift.register.node=/utils/cert -Dthrift.register.port=55557 -jar demo-jlpay-rpc-0.0.1-SNAPSHOT.jar >>cert.log &
 
```

停止
```
jps -lmv |grep 555
kill xxx
```