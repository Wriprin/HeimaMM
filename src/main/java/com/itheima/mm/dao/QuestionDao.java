package com.itheima.mm.dao;

import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.Tag;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询总条数
     * @param queryPageBean
     * @return
     */
    Long findTotalBasicCount(QueryPageBean queryPageBean);

    /**
     * 查询当前页数据集合
     * @param queryPageBean
     * @return
     */
    List<Question> findBasicListCount(QueryPageBean queryPageBean);

    /**
     * 添加基础试题
     * @param question
     */
    void addBasicQuestion(Question question);

    /**
     * 添加题目关联标签
     * @param tagMap
     */
    void addQuestionTag(Map tagMap);
}
