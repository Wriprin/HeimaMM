package com.itheima.mm.service;

import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.utils.IntegerUtils;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: wriprin
 * @Date: 2021/10/26/026 17:14:26
 * @Version 1.0
 */
public class QuestionService {
    /**
     * 查询学科种类列表
     * @param parameterMap
     * @return
     */
    public List<Map> findCategories(Map parameterMap) throws IOException {
        // 获取 categoryKind 和 categoryType
        Integer categoryKind = IntegerUtils.getInteger(parameterMap.get("categoryKind"));
        Integer categoryType = IntegerUtils.getInteger(parameterMap.get("categoryType"));

        /**
         * categoryKind(种类):
         *      1 - 学科目录
         *      2 - 企业
         *      3 - 行业  （略）
         * categoryType(类型):
         *      101 - 刷题
         *      201 - 错题本    （略）
         *      202 - 我的练习  （略）
         *      203 - 收藏题目  （略）
         */
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);

        // 对 categoryType 和 categoryKind 判断
        List<Map> questionList = null;
        if (101 == categoryType) {
            // 101 - 刷题
            if (1 == categoryKind) {
                // 学科目录
                questionList = questionDao.findCategoryByTag(parameterMap);
            } else if (2 == categoryKind) {
                // 企业目录
                questionList = questionDao.findCategoryByCompany(parameterMap);
            }
        }
        return questionList;
    }
}
