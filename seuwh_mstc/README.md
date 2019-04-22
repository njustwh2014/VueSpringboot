**打包命令** mvn clean package -Dmaven.test.skip=true
**nohup**  nohup java -jar seuwh_mstc-0.0.1-SNAPSHOT.jar > nohup.out 2>&1 &
## 2019/4/16
### 1.将文章发布默认修改为review
## 2019/4/4
### 1.实现文章状态管理
## 2019/3/25
### 1.根据articleid阅读文章
```sql
select t1.id,t1.title,t2.commentcount,t2.viewcount,t1.summary,t1.author as authorid,t5.nickname,t1.category,t5.headportraiturl as authoravtar,
            t3.id as categoryid,t3.categorydescription,t1.publishtime,t4.content from 
            (((articles as t1 inner join articleviewinfo as t2 on t1.id=t2.articleid)
            inner join category as t3 on t1.category=t3.id)
            inner join articlebody as t4 on t1.id=t4.articleid)
            inner join user_information as t5 on t1.author=t5.id and t1.id=#{id} limit 1;
```
### 2.文章获取tag
```sql
select t1.tagid,t2.tagdescription from articletag as t1 inner join tag as t2 on t1.tagid=t2.id and t1.articleid=#{articleid}
```
### 3.修改了发布和阅读文章接口，便于一次操作数据库，尽量使用Map
## 2019/3/24
### 1.实现了文章按category的status加载
## 2019/3/19
### 1.利用模糊查询实现搜索功能，需要改进！！！！！！！！！！！！
## 2019/3/18
### 1. 利用redis实现了消息队列，处理了viewcount和commentcount以及计算了weight
将viewcount和commentcount异步写入了mysql,便于后面操作
### 2. 实现了文章在线修改

### 当日任务：
+ 利用redis实现消息队列
+ 对每个输入进行边缘检测
+ 如果有时间，写一个单元测试
## 2019/3/17
### 1.实现用户信息更新
## 2019/3/15
### 1.添加了jedis
### 2.为了实现点赞功能，在commentsend的model里添加了likecount和islike字段，便于加载文章时获取用户点赞信息
### 3.redis持久化配置：https://blog.csdn.net/guweiyu_thinker/article/details/78816071

## 2019/3/14

### 1.给articles表添加了一栏: cover 用作文章封面


## 2019/3/12

### 1. 连表查询category和tag

## 2019/3/11
### 1.建立articleweight表

### 2.实现热度排行
目前$weight=viewcount \times 2+commentcount \times 10$;

## 2019/3/8

### 1. 连表查询
建立Map返回值
```java
@Select({"select t1.id,t1.author,t1.publishtime,t2.content from articles as t1 inner join articlebody as t2 on t1.id=t2.articleid"})
List<HashMap<String,Object>> LinkTableQuery();
```
### 2. 实现ArticleController.getAllArticle()通过连表查询
**API:**
```javascript
status:
msg:
data:{
  list:[listItem...],
  listItem:{
    title,
    summary,
    publishtime,
    id,//article id
    author:{
      id,//user id
      nickname
    },
    commentcount,
    viewcount,
    tags:{//array
      id,
      articleid,
      tagdescription,
    },
    weight
  }
}
```
具体实现：
```java
// 首页获取文章简要信息
@Override
public ResultInfo getAllArticle(Integer pageNumber, Integer pageSize) {
    List<Map<String,Object>> articleslist=articleLinkTableDao.GetAllArticle((pageNumber-1)*pageSize,pageSize);
    for(Map<String,Object> item:articleslist){
        item.put("tags",articleTagDao.SelectByArticleId(Integer.parseInt(item.get("id").toString())));
    }
    return ResultInfo.ok(articleslist);
}
```
减少了一次ajax请求对于mysql的访问连接次数。



## Mysql学习
联表查询：https://www.cnblogs.com/withscorpion/p/9454490.html

# 设计数据库
## 1.用户表设计

## 2.articles表设计

### article关键信息表
id
author
publishtime
title
summary
category

```sql
create table articles(
  id int(11) not null primary key auto_increment,
  author varchar(255) not null,
  publishtime datetime,
  title varchar(255) not null,
  summary text not null,
  category int(11) not null,
  categorydescription varchar(255) not null,
  cover varchar(255)
);
```
### article标签表
id
articleid
tagid
```sql
create table articletag(
  id int(11) not null primary key auto_increment,
  articleid int(11) not null,
  tagid int(11) not null,
  tagdescription varchar(255)
);
```
### 标签对应表
id
tagdesscription
```sql
create table tag(
  id int(11) not null primary key auto_increment,
  tagdescription varchar(255)
);
```

### 分类对应表
id
categorydescription
```sql
create table category(
  id int(11) not null primary key auto_increment,
  categorydescription varchar(255)
);
```

### article主体
id
articleid
content
contenthtml

```sql
create table articlebody(
  id int(11) not null primary key auto_increment,
  articleid int(11) not null,
  content longtext not null,
  contenthtml longtext not null
);
```

### article访问信息
后面利用redis实现
id
articleid
viewcount
commentcount
```sql
create table articleviewinfo(
  id int(11) not null primary key auto_increment,
  articleid int(11) not null,
  viewcount int(11) not null,
  commentcount int(11) not null
);
```

## 3.评论表设计
id
content
createdate
articleid
authorid
parentid
toid
atlevel
likecount

```sql
create table articlecomment(
  id int(11) not null primary key auto_increment,
  content text,
  createdate datetime not null,
  articleid int(11) not null,
  authorid int(11) not null,
  parentid int(11) not null,
  toid int(11) not null,
  atlevel int not null,
  likecount int(11) not null
);
```

## 4.热度排行
```sql
create table articleweight(
  id int(11) not null primary key auto_increment,
  articleid int(11) not null,
  weight int(11) not null
);
```
