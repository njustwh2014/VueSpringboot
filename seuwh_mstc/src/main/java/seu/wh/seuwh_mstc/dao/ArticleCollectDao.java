/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package seu.wh.seuwh_mstc.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Mapper
public interface ArticleCollectDao {
    @Insert({"insert into articlecollect (userid,articleid) values(#{userid},#{articleid}) "})
    int addCollect(@Param("userid")Integer userid,@Param("articleid")Integer articleid);

    @Select({"select id,userid,articleid from articlecollect where articleid=#{articleid} and userid=#{userid} limit 1"})
    Map<String,Object> selectCollect(@Param("userid")Integer userid, @Param("articleid")Integer articleid);
}
