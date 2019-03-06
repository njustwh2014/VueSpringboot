# blogapp

> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run e2e tests
npm run e2e

# run all tests
npm test
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).



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