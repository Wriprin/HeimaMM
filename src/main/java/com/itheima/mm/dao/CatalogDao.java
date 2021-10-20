package com.itheima.mm.dao;

import com.itheima.mm.pojo.Catalog;

/**
 * @Author: wriprin
 * @Date: 2021/10/19/019 10:00:07
 * @Version 1.0
 */
public interface CatalogDao {
    /**
     * 根据 course.id 查询关联数据条数
     * @param id
     * @return
     */
    Long findById(Integer id);

    Catalog findIdNameByCourseId(Integer id);
}
