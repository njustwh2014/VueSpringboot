# MyWebLearnPath
## servlet学习
servlet+idea环境配置参考：https://www.cnblogs.com/javabg/p/7976977.html

centos上安装mysql：https://www.cnblogs.com/starof/p/4680083.html 

centos上部署springboot 项目： https://www.cnblogs.com/zuidongfeng/p/8859229.html

centos上配置fdfs： https://blog.csdn.net/chen3888015/article/details/70172505/

微笑大神： 使用fdfs: http://www.ityouknow.com/springboot/2018/01/16/spring-boot-fastdfs.html

HTTP请求不能访问文件的原因：https://blog.csdn.net/qq_34301871/article/details/80060235

出现缺少fastcommon.h修改办法： https://blog.csdn.net/babydavic/article/details/21240021
nginx安装：https://blog.csdn.net/qq_36922927/article/details/79554806



## Mysql学习
联表查询：https://www.cnblogs.com/withscorpion/p/9454490.html

## springColud
Spring Boot 中使用Feign作为HTTP客户端调用远程HTTP服务： https://blog.csdn.net/u010541670/article/details/80068575

## redis学习

### redis简介
redis是用C语言开发的一个开源的高性能键值对（key-value）数据库。它通过提供多种键值数据类型来适应不同场景下的存储需求，目前为止redis支持的键值数据类型如下字符串(string)、列表（list）、集合（set）、有序集合（sorts sets(zset)）、哈希表（hash）
### redis应用场景
+ 缓存（数据查询、短连接、新闻内容、商品内容等等）。（最多使用）
+ 分布式集群架构中的session分离
+ 聊天室的在线好友列表
+ 任务队列（秒杀、抢购、12306等等） 
+ 应用排行榜
+ 网站访问统计 
+ 数据过期处理（可以精确到毫秒）
+ ···

### redis在centos上安装教程
https://www.cnblogs.com/herblog/p/9305668.html

https://blog.csdn.net/qq_32092723/article/details/81163642

### 备忘
#### centos端
+ 安装在 /usr/local/redis
+ 启动命令
```shell
cd /usr/local/redis/bin
./redis-server ./redis.conf
```
+ 连接与退出
```shell
cd /usr/local/redis/bin
./redis-cli
quit
```
+ 关闭命令
```shell
cd /usr/local/redis
./bin/redis-cli shutdown
```
+ 强行终止
```shell
pkill redis-server
```
+ 开机自启动redis
```shell
vim /etc/rc.local
//添加
/usr/local/redis/bin/redis-server /usr/local/redis/etc/redis-conf
```