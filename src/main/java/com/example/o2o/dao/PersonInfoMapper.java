package com.example.o2o.dao;

import com.example.o2o.entity.PersonInfo;
import org.springframework.stereotype.Repository;

/**
 * @author kylin
 * @create 2020/6/4 20:22
 */

@Repository
public interface PersonInfoMapper {

    // @Select("SELECT user_id, name, profile_img, email, gender, enable_status, user_type, create_time, last_edit_time FROM tb_person_info WHERE user_id = #{userId}")
    PersonInfo queryPersonInfoById(long userId);

    // @Insert("INSERT INTO tb_person_info(name,profile_img,email,gender,enable_status,user_type,create_time,last_edit_time) " +
    //         "VALUES (#{name},#{profileImg},#{email},#{gender},#{enableStatus},#{userType},#{createTime},#{lastEditTime})")
    int insertPersonInfo(PersonInfo personInfo);


    
}
