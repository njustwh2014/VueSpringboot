## 2019/3/12

### 1. 连表查询category和tag

## 2019/3/11
### 1.建立articleweight表

### 2.实现热度排行
目前weight=viewcount*2+commentcount*2;

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
  categorydescription varchar(255)
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
