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
import seu.wh.seuwh_mstc.model.ArticleCollectItem;

import java.util.Map;

@Component
@Mapper
public interface ArticleCollectDao {
    /*
    * Table Name： articlecollect
    * 字段：
    * id int(11) not null primary key auto_increment,
      articleid int(11) not null,
      userid int(11) not null
    * */
    //添加收藏
    @Insert({"insert into articlecollect (userid,articleid) values(#{userid},#{articleid}) "})
    int addCollect(ArticleCollectItem articleCollectItem);

    //查某个收藏是否已存在
    @Select({"select id,userid,articleid from articlecollect where articleid=#{articleid} and userid=#{userid} limit 1"})
    Map<String,Object> selectCollect(@Param("userid")Integer userid, @Param("articleid")Integer articleid);

    //取消收藏
    @Delete({"delete from articlecollect where articleid=#{articleid} and userid=#{userid}"})
    int deleteCollect(@Param("userid")Integer userid,@Param("articleid")Integer articleid);

}
