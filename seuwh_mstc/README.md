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