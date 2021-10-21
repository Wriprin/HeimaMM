package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.constants.Constants;
import com.itheima.mm.entry.PageResult;
import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.entry.Result;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.QuestionService;
import com.itheima.mm.utils.DateUtils;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author: wriprin
 * @Date: 2021/10/21/021 7:32:57
 * @Version 1.0
 */

@Controller
public class QuestionController {
    private QuestionService questionService = new QuestionService();

    /**
     * 分页查询基础试题
     */
    @RequestMapping("/question/findByPage")
    public void findByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 接收请求参数，封装到 queryPageBean 对象中
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(request, QueryPageBean.class);

            // 调用 业务层，分页查询
            PageResult pageResult = questionService.findByPage(queryPageBean);

            // 查询成功
            JsonUtils.printResult(response, new Result(true, "基础试题查询成功", pageResult));

        } catch (Exception e) {
            e.printStackTrace();
            // 查询失败
            JsonUtils.printResult(response, new Result(false, "基础试题查询失败"));
        }
    }

    /**
     * 添加基础试题
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/question/addBasicQuestion")
    public void addBasicQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 获取请求参数，封装到 question 对象中
            Question question = JsonUtils.parseJSON2Object(request, Question.class);
            // 补全 status、reviewStatus、createDate、userId
            question.setStatus(Constants.QUESTION_PRE_PUBLISH);
            question.setReviewStatus(Constants.QUESTION_PRE_REVIEW);
            question.setCreateDate(DateUtils.parseDate2String(new Date()));
            User user = (User) request.getSession().getAttribute(Constants.LOGIN_USER);
            question.setUserId(user.getId());

            // 调用 业务层，添加数据
            questionService.addBasicQuestion(question);

            // 添加成功，响应结果
            JsonUtils.printResult(response, new Result(true, "添加 基础试题 成功"));


        } catch (Exception e) {
            e.printStackTrace();
            // 添加失败
            JsonUtils.printResult(response, new Result(false, "添加 基础试题 失败"));
        }


    }

}
