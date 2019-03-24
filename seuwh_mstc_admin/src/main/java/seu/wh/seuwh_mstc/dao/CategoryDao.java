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
import seu.wh.seuwh_mstc.model.Category;

import java.util.List;
import java.util.Map;

@Component
@Mapper

public interface CategoryDao {
    String TABLE_NAME=" category ";
    String INSERT_FIELDS=" categorydescription,categorystatus";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;

    @Select({"select * from"+TABLE_NAME+"ORDER BY id limit #{start},#{end}"})
    List<Map<String,Object>> getAllCategory(@Param("start")Integer start,@Param("end")Integer end);

    @Select({"SELECT COUNT(*) FROM "+TABLE_NAME})
    Integer countCategory();

    @Update({"UPDATE category SET categorystatus=#{categorystatus} WHERE id=#{id}"})
    void changeStatus(@Param("id")Integer id,@Param("categorystatus")String categorystatus);

    @Delete({"DELETE FROM category WHERE id=#{id}"})
    Integer deleteCategory(Integer id);

    @Insert({"INSERT INTO ",TABLE_NAME,"(",INSERT_FIELDS,") values(#{categorydescription},#{categorystatus})"})
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void addCategory(Category category);

    @Select({"select id from ",TABLE_NAME,"where categorydescription=#{categorydescription} limit 1"})
    Integer selectIdByDescription(String categoryDescription);
}
