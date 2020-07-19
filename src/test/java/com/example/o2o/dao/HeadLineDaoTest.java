package com.example.o2o.dao;

import com.example.o2o.entity.HeadLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author kylin
 * @create 2020/6/5 18:39
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeadLineDaoTest {

    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testQueryHeadLine() {
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(0);
        List<HeadLine> headLineList = headLineDao.queryHeadLine(headLine);
        System.out.println(headLineList.size());
    }

}
