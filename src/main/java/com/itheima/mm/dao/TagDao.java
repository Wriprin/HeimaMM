package com.itheima.mm.dao;

import com.itheima.mm.pojo.Tag;

import java.util.List;

/**
 * @Author: wriprin
 * @Date: 2021/10/19/019 10:05:23
 * @Version 1.0
 */
public interface TagDao {
    /**
     * 根据 course.id 查询关联标签条数
     * @param id
     * @return
     */
    Long findById(Integer id);

    /**
     * 根据 course.id 查询关联标签 id, name
     * @param id
     * @return
     */
    List<Tag> findIdNameByCourseId(Integer id);
}
