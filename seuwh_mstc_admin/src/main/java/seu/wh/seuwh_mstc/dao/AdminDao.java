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
import seu.wh.seuwh_mstc.model.Admin;

@Component
@Mapper
public interface AdminDao {

    String TABLE_NAME=" admininfo ";
    String INSERT_FIELDS=" username,password,salt,token";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;

    @Select({"select * from ",TABLE_NAME,"where username=#{username}"})
    Admin selectByUsername(String username);

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values(username=#{username},password=#{password},salt=#{salt},token=#{token})"})
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertAdmin(Admin admin);

    @Update({"update ",TABLE_NAME,"set token=#{token} where id=#{id}"})
    void updateToken(Admin admin);

}
