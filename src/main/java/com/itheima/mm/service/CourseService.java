package com.itheima.mm.service;

import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * @Author: wriprin
 * @Date: 2021/10/19/019 8:26:51
 * @Version 1.0
 */
public class CourseService {
    /**
     * 添加学科
     * @param course
     * @throws Exception
     */
    public void add(Course course) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);

        // 调用 dao 添加 学科
        courseDao.add(course);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }
}
