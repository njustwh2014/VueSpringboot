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
import seu.wh.seuwh_mstc.model.Tag;

import java.util.List;
import java.util.Map;

@Component
@Mapper

public interface TagDao {

    String TABLE_NAME=" tag ";
    String INSERT_FIELDS=" tagdescription,tagstatus,categoryid";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;



//    @Select({"select * from"+TABLE_NAME+"ORDER BY id limit #{start},#{end}"})
    @Select({"select tag.id,tag.tagdescription,tag.tagstatus,category.categorydescription  from tag inner join category on tag.categoryid=category.id order by tag.id limit #{start},#{end}"})
    List<Map<String,Object>> getAllTag(@Param("start")Integer start, @Param("end")Integer end);

    @Select({"select id,tagdescription from"+TABLE_NAME+"where categoryid=#{categoryid} and tagstatus='show'"})
    List<Map<String,Object>> getTagByCategoryid(Integer categoryid);

    @Select({"SELECT COUNT(*) FROM "+TABLE_NAME})
    Integer countTag();

    @Delete({"DELETE FROM tag WHERE id=#{id}"})
    Integer deleteTag(Integer id);

    @Select({"select id from ",TABLE_NAME,"where tagdescription=#{tagdescription} and categoryid=#{categoryid} limit 1"})
    Integer selectIdByDescriptionAndCategoryid(@Param("tagdescription") String tagdescription,@Param("categoryid") Integer categoryid);

    @Insert({"INSERT INTO ",TABLE_NAME,"(",INSERT_FIELDS,") values(#{tagdescription},#{tagstatus},#{categoryid})"})
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void addCategory(Tag tag);
}
