package com.itheima.mm.dao;

import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.QuestionItem;

/**
 * @Author: wriprin
 * @Date: 2021/10/21/021 16:07:37
 * @Version 1.0
 */
public interface QuestionItemDao {
    /**
     * 添加试题选项
     * @param questionItem
     */
    void add(QuestionItem questionItem);
}
