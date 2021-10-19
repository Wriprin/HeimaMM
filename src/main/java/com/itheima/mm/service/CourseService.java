package com.itheima.mm.service;

import com.itheima.mm.dao.CatalogDao;
import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.TagDao;
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

    /**
     * 根据 id 删除指定学科
     * @param id
     */
    public void delete(Integer id) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        // 删除之前，先判断该条数据有没有外键关联数据
        // 1. 二级目录 t_catalog
        CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
        Long catalogCount = catalogDao.findById(id);

        // 2. 标签 t_tag
        TagDao tagDao = sqlSession.getMapper(TagDao.class);
        Long tagCount = tagDao.findById(id);

        // 3. 题目数量 t_question
        QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
        Long questionCount = questionDao.findById(id);


        if (catalogCount > 0) {
            throw new RuntimeException("存在二级目录，无法删除");
        }
        if (tagCount > 0) {
            throw new RuntimeException("存在标签，无法删除");
        }
        if (questionCount > 0) {
            throw new RuntimeException("存在题目，无法删除");
        }

        if (catalogCount + tagCount + questionCount == 0) {
            // 该条数据没有任何外键关联数据，可以删除
            CourseDao courseDao = sqlSession.getMapper(CourseDao.class);
            // 调用 dao，删除指定 id 的学科
            courseDao.delete(id);
            SqlSessionFactoryUtils.commitAndClose(sqlSession);
        }
    }
}
