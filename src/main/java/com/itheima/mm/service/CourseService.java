package com.itheima.mm.service;

import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.entry.PageResult;
import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

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

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    public PageResult findByPage(QueryPageBean queryPageBean) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
        PageResult pageResult = new PageResult();

        // 查询总条数
        Long total = courseDao.findTotal();
        pageResult.setTotal(total);

        // 查询每页结果
        List<Course> courseList = courseDao.findListByPage(queryPageBean);
        pageResult.setRows(courseList);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);

        return pageResult;
    }

    /**
     * 更新学科信息
     * @param course
     */
    public void update(Course course) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);

        courseDao.update(course);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }
}
