package com.itheima.mm.dao;

import com.itheima.mm.entry.PageResult;
import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.pojo.Course;

import java.util.List;

/**
 * @Author: wriprin
 * @Date: 2021/10/19/019 8:29:19
 * @Version 1.0
 */
public interface CourseDao {
    /**
     * 添加学科
     * @param course
     */
    void add(Course course);

    /**
     * 查询总条数
     * @return
     */
    Long findTotal();

    /**
     * 查询每页结果
     * @param queryPageBean
     * @return
     */
    List<Course> findListByPage(QueryPageBean queryPageBean);

    /**
     * 更新学科信息
     * @param course
     */
    void update(Course course);
}
