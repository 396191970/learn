# docker 基本操作
## 依赖
docker for windows 

需要配置共享
## 安装mysql数据库
```aidl
 docker pull mysql:5.6
```
## 运行mysql
```aidl
docker run --rm -p 3306:3306 --name mymysql -v /d/lsf/JavaDevelop/mysql/conf:/etc/mysql/conf.d -v /d/lsf/JavaDevelop/mysql/logs:/logs -v /d/lsf/JavaDevelop/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6
```

命令说明：

-p 3306:3306：将容器的 3306 端口映射到主机的 3306 端口。

-v -v $PWD/conf:/etc/mysql/conf.d：将主机当前目录下的 conf/my.cnf 挂载到容器的 /etc/mysql/my.cnf。

-v $PWD/logs:/logs：将主机当前目录下的 logs 目录挂载到容器的 /logs。

-v $PWData:/var/lib/mysql ：将主机当前目录下的data目录挂载到容器的 /var/lib/mysql 。

-e MYSQL_ROOT_PASSWORD=123456：初始化 root 用户的密码。

## 查看启动信息
```
docker inspect mymysql
```


## 密码加密
如今Spring Security中密码的存储格式是“{id}…………”。前面的id是加密方式，id可以是bcrypt、sha256等，后面跟着的是加密后的密码。也就是说，程序拿到传过来的密码的时候，会首先查找被“{”和“}”包括起来的id，来确定后面的密码是被怎么样加密的，如果找不到就认为id是null。这也就是为什么我们的程序会报错：There is no PasswordEncoder mapped for the id “null”。

BCryptPasswordEncoder 来加解密
举例
```aidl
{bcrypt}$2a$10$I03d9qdTlmbQMC9XeCRlS.rcSx7lqhvIMynhocTcHHpeBa9A3GOD.

```