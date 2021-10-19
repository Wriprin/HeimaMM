package com.itheima.mm.dao;

/**
 * @Author: wriprin
 * @Date: 2021/10/19/019 10:09:10
 * @Version 1.0
 */
public interface QuestionDao {
    /**
     * 根据 course.id 查询关联数据
     * @param id
     * @return
     */
    Long findById(Integer id);
}
