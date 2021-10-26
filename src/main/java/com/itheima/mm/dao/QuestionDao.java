package com.itheima.mm.dao;

import java.util.List;
import java.util.Map;

/**
 * @Author: wriprin
 * @Date: 2021/10/26/026 18:42:30
 * @Version 1.0
 */
public interface QuestionDao {
    /**
     * 学科目录
     * @return
     * @param parameterMap
     */
    List<Map> findCategoryByTag(Map parameterMap);

    /**
     * 企业目录
     * @return
     * @param parameterMap
     */
    List<Map> findCategoryByCompany(Map parameterMap);
}
