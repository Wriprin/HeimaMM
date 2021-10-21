package com.itheima.mm.service;

import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.entry.PageResult;
import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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
}
