/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.dao;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import seu.wh.seuwh_mstc.model.ArticleInfo;

import java.util.List;

@Component
@Mapper
public interface ArticleInfoDao {

    String TABLE_NAME=" articles ";
    String INSERT_FIELDS=" author,publishtime,title,summary,category,categorydescription,cover";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{author},#{publishtime},#{title},#{summary},#{category},#{categorydescription},#{cover})"})
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int addArticle(ArticleInfo articleInfo);

    @Update({"update ",TABLE_NAME, " set title=#{title},publishtime=#{publishtime}, summary=#{summary},category=#{category},categorydescription=#{categorydescription} where id=#{id}"})
    int updateArticle(ArticleInfo articleInfo);
    @Select({"select * from ",TABLE_NAME,"where id=#{id}"})
    ArticleInfo selectByid(Integer id);

    @Select({"select * from",TABLE_NAME,"order by publishtime desc limit #{pageNumber},#{pageSize} "})
    List<ArticleInfo> getArticleByIdRange(@Param("pageNumber") Integer pageNumber,@Param("pageSize") Integer pageSize);

    @Select({"select count(*) from ",TABLE_NAME})
    Integer countArticles();

    @Update({"UPDATE articles SET articlestatus=#{articlestatus} WHERE id=#{id}"})
    void changeStatus(@Param("id")Integer id,@Param("articlestatus")String articlestatus);
}
