/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface ArticleLinkTableDao {


    //获取首页文章简要信息
    /*
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
    * */
    @Select({"select t1.id,t1.title,t1.summary,t1.publishtime,t1.author,t1.articlestatus,t2.commentcount,t2.viewcount,t3.nickname,t3.id as userid"+
            " from "+
            " (articles as t1 inner join articleviewinfo as t2 on t1.id=t2.articleid)"+
            " inner join user_information as t3 on t1.author=t3.id"+
            " order by t1.publishtime desc limit #{pageNumber},#{pageSize}"})
//    List<Map<String,Object>> GetAllArticle(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);
    List<Map<String,Object>> GetAllArticle(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);

    @Select({"select t1.id,t1.title,t1.summary,t1.publishtime,t1.author,t2.commentcount,t2.viewcount,t3.nickname,t3.id as userid"+
            " from "+
            " (articles as t1 inner join articleviewinfo as t2 on t1.id=t2.articleid and t1.category=#{id})"+
            " inner join user_information as t3 on t1.author=t3.id"+
            " order by t1.publishtime desc limit #{pageNumber},#{pageSize}"})
    List<Map<String, Object>> GetArticlesByCategory(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize, @Param("id") Integer id);

    @Select({"select t1.id,t1.title,t1.summary,t1.publishtime,t1.author,t2.commentcount,t2.viewcount,t3.nickname,t3.id as userid"+
            " from "+
            " ((articletag as t4 inner join articles as t1 on t1.id=t4.articleid and t4.tagid=#{id}) inner join articleviewinfo as t2 on t1.id=t2.articleid)"+
            " inner join user_information as t3 on t1.author=t3.id"+
            " order by t1.publishtime desc limit #{pageNumber},#{pageSize}"})
    List<Map<String, Object>>  GetArticlesByTag(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize, @Param("id") Integer id);

    @Select({"select t1.id,t1.title,t1.summary,t1.publishtime,t1.author,t2.commentcount,t2.viewcount,t3.nickname,t3.id as userid"+
            " from "+
            " ((articles as t1 inner join articlebody as t4 on t1.id=t4.articleid and (t1.title like CONCAT('%',#{keywords},'%') or t4.content like CONCAT('%',#{keywords},'%')))"+
            " inner join user_information as t3 on t1.author=t3.id)"+
            " inner join articleviewinfo as t2 on t1.id=t2.articleid"+
            " order by t1.publishtime desc limit #{pageNumber},#{pageSize}"})
    List<Map<String,Object>> getAllArticleByKeyWords(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize,@Param("keywords") String keywords);



    //表articletag和articlecomment设置了外键关联了articles，当articles有删除更新时，articletag和articlecomment对应记录也会更新
    @Delete({"DELETE articles,articlebody,articleviewinfo,articleweight",
            " FROM articles LEFT JOIN articlebody ON articles.id=articlebody.articleid",
            " LEFT JOIN articleviewinfo ON articles.id=articleviewinfo.articleid",
            " LEFT JOIN articleweight ON articles.id=articleweight.articleid",
            " where articles.id=#{articleid};"})
    int deleteArticleByid(Integer articleid);

    @Delete({"DELETE articles,articlebody,articleviewinfo,articleweight",
            " FROM articles LEFT JOIN articlebody ON articles.id=articlebody.articleid",
            " LEFT JOIN articleviewinfo ON articles.id=articleviewinfo.articleid",
            " LEFT JOIN articleweight ON articles.id=articleweight.articleid",
            " where articles.category=#{categoryid};"})
    int deleteArticleByCategoryid(Integer categoryid);

}
