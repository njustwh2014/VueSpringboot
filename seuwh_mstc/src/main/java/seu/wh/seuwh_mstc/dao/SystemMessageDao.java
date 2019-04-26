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
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import seu.wh.seuwh_mstc.model.SystemMessage;

import java.util.List;
import java.util.Map;

/*
* table name: systemmessage
* func：系统消息表
* 字段：
* id int(11) not null primary key auto_increment,
  messagetype int(11) not null,
  entityid int(11) not null,
  entitytype int(11) not null,
  entityauthor int(11) not null,
  listener int(11) not null,
  messagestatus int(11) not null,
  sender int(11) not null,
  messagedate datetime not null,
  messagecontent text
* */

@Component
@Mapper
public interface SystemMessageDao {
    String TABLE_NAME=" systemmessage ";
    String INSERT_FIELDS=" messagetype,entityid,entitytype,entityauthor,listener,messagestatus,sender,messagedate,messagecontent";
    String SELECT_FIELDS="id, "+INSERT_FIELDS;

    @Insert({"insert into "+TABLE_NAME+"("+INSERT_FIELDS+") values(#{messagetype},#{entityid},#{entitytype},#{entityauthor},#{listener},#{messagestatus},#{sender},#{messagedate},#{messagecontent})"})
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int addSystemmessage(SystemMessage systemMessage);

    /*
    * 获取某用户未读消息s数
    * */
    @Select({"select count(id) from "+TABLE_NAME+"where listener=#{userid} and messagestatus=0"})
    Integer getUserUnreadStatus(Integer userid);

    /*
    * 获取前4条未读消息
    * */
    @Select({"select * from "+TABLE_NAME+"where listener=#{userid} and messagestatus=0 limit 4"})
    List<SystemMessage> getFourUnreadMessage(Integer userid);
}
