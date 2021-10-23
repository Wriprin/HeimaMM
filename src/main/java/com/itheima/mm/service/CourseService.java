package com.itheima.mm.service;

import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * @Author: wriprin
 * @Date: 2021/10/23/023 7:54:29
 * @Version 1.0
 */
public class CourseService {

    /**
     * 查询所有学科
     * @return
     */
    public List<Course> getCourseList() throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);

        // 调用 dao 层，查询所有学科
        List<Course> courseList = courseDao.getCourseList();

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return courseList;
    }
}
