/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.dao;

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
    @Select({"select t1.id,t1.title,t1.summary,t1.publishtime,t1.author,t2.commentcount,t2.viewcount,t3.nickname,t3.id as userid"+
            " from "+
            " (articles as t1 inner join articleviewinfo as t2 on t1.id=t2.articleid)"+
            " inner join user_information as t3 on t1.author=t3.id"+
            " order by t1.publishtime desc limit #{pageNumber},#{pageSize}"})
//    List<Map<String,Object>> GetAllArticle(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);
    List<Map<String,Object>> GetAllArticle(@Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize);
}
