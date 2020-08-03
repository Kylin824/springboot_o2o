package com.example.o2o.dao;

import com.example.o2o.entity.PersonInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author kylin
 * @create 2020/6/4 20:33
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonInfoDaoTest {

    @Autowired
    private PersonInfoDao personInfoDao;

    @Test
    public void testQueryPersonInfoById() {
        PersonInfo p = personInfoDao.queryPersonInfoById(3);
        System.out.println(p.toString());
    }

    @Test
    public void testInsertPersonInfo() {
        PersonInfo p = new PersonInfo();
        p.setName("huya2");
        p.setGender("m");
        p.setEmail("test@test");
        p.setEnableStatus(0);
        p.setCreateTime(new Date());
        p.setLastEditTime(new Date());

        System.out.println(personInfoDao.insertPersonInfo(p));
        System.out.println(p.getUserId());
    }
}
