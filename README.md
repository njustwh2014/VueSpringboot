# MyWebLearnPath
## 阿里云配置
项目文件放置在：/home/huanhuan/myweb
## docker安装
参考教程：https://blog.csdn.net/u010046908/article/details/79553227
```bash
yum update -y
yum -y install docker
systemctl start docker
```
## docker安装nginx
```bash
docker pull nginx
#创建/home/huanhuan/myweb/nginxdata并进入
mkdir /home/huanhuan/myweb/nginxdata
cd /home/huanhuan/myweb/nginxdata
# 创建conf
mkdir conf
# 复制主机nginx.conf到服务器的conf文件夹内
# 创建容器
docker run -d -p 3000:3000 -p 3100:3100 --name nginx-web -v /home/huanhuan/myweb/myvue:/home/huanhuan/myweb/myvue -v /home/huanhuan/myweb/images:/home/huanhuan/myweb/images -v /home/huanhuan/myweb/myvueadmin:/home/huanhuan/myweb/myvueadmin -v /home/huanhuan/myweb/myvueadmin:/home/huanhuan/myweb/myvueadmin -v /home/huanhuan/myweb/nginxdata/conf/nginx.conf:/etc/nginx/nginx.conf nginx
```
## docker安装mysql
```bash
# pull image
docker pull mysql:latest
# run
docker run --name mysql-mstc -v /home/huanhuan/myweb/mysqldata:/data -e MYSQL_ROOT_PASSWORD=123456 -d -i -p 3306:3306 --restart=always  mysql:latest
# 导出sql文件
mysqldump -u root -p seumstc > F:/seumstc.sql
# docker容器内导入sql文件
# 进入docker容器
docker exec -it mysql-mstc bash
# 连接mysql
mysql -u root -p
# 新建数据库
create database seumstc;
use seumstc;
# 导入sql文件
source /data/seumstc.sql;
```
### 解决mysql Client does not support authentication protocol requested by server; consider upgrading MySQL错误
```sql
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
SELECT plugin FROM mysql.user WHERE User = 'root';
```
## Elasticsearch
https://www.cnblogs.com/dreamroute/p/8484457.html

## servlet学习
servlet+idea环境配置参考：https://www.cnblogs.com/javabg/p/7976977.html

centos上安装mysql：https://www.cnblogs.com/starof/p/4680083.html 

centos上部署springboot 项目： https://www.cnblogs.com/zuidongfeng/p/8859229.html

centos上配置fdfs： https://blog.csdn.net/chen3888015/article/details/70172505/

微笑大神： 使用fdfs: http://www.ityouknow.com/springboot/2018/01/16/spring-boot-fastdfs.html

HTTP请求不能访问文件的原因：https://blog.csdn.net/qq_34301871/article/details/80060235

出现缺少fastcommon.h修改办法： https://blog.csdn.net/babydavic/article/details/21240021
nginx安装：https://blog.csdn.net/qq_36922927/article/details/79554806


## Spring学习
Spring事务原理：http://www.codeceo.com/article/spring-transactions.html
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
### redis几种数据结构一般应用场景
+ List: 双向列表，适用于最新列表，关注列表；
+ Set: 适用于无顺序的集合，点赞点踩，抽奖，已读，共同好友；
+ SortedSet : 具有排序加成功能，适用于排行榜，优先队列的实现；
+ Hash：对象属性，不定长属性数；
+ KV : 单一数值，适用于验证码，缓存等实现。
### redis在centos上安装教程
https://www.cnblogs.com/herblog/p/9305668.html

https://blog.csdn.net/qq_32092723/article/details/81163642

### redis持久化配置
https://blog.csdn.net/guweiyu_thinker/article/details/78816071
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
#### windows端
+ 在D:\my_program\Redis-x64-3.2.100目录下
+ 启动
```powershell
redis-server.exe redis.windows.conf
```
+ 重开一个gitbash，连接
```powershell
redis-cli.exe -h 127.0.0.1 -p 6379
```
+ 注册为系统服务
```powershell
redis-server --service-install redis.windows-service.conf
redis-server --service-start
redis-server --service-stop
redis-server --service-uninstall//卸载服务
```
