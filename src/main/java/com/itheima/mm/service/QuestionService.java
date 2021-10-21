package com.itheima.mm.service;

import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.QuestionItemDao;
import com.itheima.mm.entry.PageResult;
import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.QuestionItem;
import com.itheima.mm.pojo.Tag;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wriprin
 * @Date: 2021/10/21/021 7:37:16
 * @Version 1.0
 */
public class QuestionService {
    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    public PageResult findByPage(QueryPageBean queryPageBean) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);

        // 查询总条数 total
        Long total = questionDao.findTotalBasicCount(queryPageBean);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);

        // 查询当前页数据集合
        List<Question> questionList = questionDao.findBasicListCount(queryPageBean);
        pageResult.setRows(questionList);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);

        return pageResult;
    }

    /**
     * 添加基础试题
     * @param question
     */
    public void addBasicQuestion(Question question) throws Exception {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtils.openSqlSession();
            QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);

            // 1. 调用 questionDao 层，添加 t_question 数据
            questionDao.addBasicQuestion(question);

            // 2. 判断 questionItemList 是否为空，如果不为空，则将所有选项添加到 tr_question_item
            // 调用 questionItemDao 层，添加 tr_question_item 数据
            QuestionItemDao questionItemDao = sqlSession.getMapper(QuestionItemDao.class);
            List<QuestionItem> questionItemList = question.getQuestionItemList();
            if (questionItemList != null && questionItemList.size() > 0) {
                // 不为空，添加数据
                for (QuestionItem questionItem : questionItemList) {
                    // 手动设置 questionId
                    questionItem.setQuestionId(question.getId());
                    // 调用 dao，添加 数据
                    questionItemDao.add(questionItem);
                }
            }

            // 3. 存储 题目和标签关联信息 到 tr_question_tag
            // 判断是否为 null
            List<Tag> tagList = question.getTagList();
            if (tagList != null && tagList.size() > 0) {
                // 不为空，添加数据
                for (Tag tag : tagList) {
                    // 存储到 map 中
                    Map tagMap = new HashMap<>();
                    tagMap.put("questionId", question.getId());
                    tagMap.put("tagId", tag.getId());
                    questionDao.addQuestionTag(tagMap);
                }
            }

            // 提交事务
            SqlSessionFactoryUtils.commitAndClose(sqlSession);

        } catch (IOException e) {
            e.printStackTrace();
            // 回滚事务
            SqlSessionFactoryUtils.rollbackAndClose(sqlSession);
        }
    }
}
