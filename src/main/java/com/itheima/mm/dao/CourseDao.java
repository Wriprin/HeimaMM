package com.itheima.mm.dao;

import com.itheima.mm.pojo.Course;

import java.util.List;

/**
 * @Author: wriprin
 * @Date: 2021/10/23/023 7:58:45
 * @Version 1.0
 */
public interface CourseDao {
    /**
     * 查询所有学科
     * @return
     */
    List<Course> getCourseList();
}
