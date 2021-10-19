package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.constants.Constants;
import com.itheima.mm.entry.PageResult;
import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.entry.Result;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.CourseService;
import com.itheima.mm.utils.DateUtils;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: wriprin
 * @Date: 2021/10/19/019 8:20:23
 * @Version 1.0
 */
@Controller
public class CourseController {
    private CourseService courseService = new CourseService();

    /**
     * 添加学科
     */
    @RequestMapping("/course/add")
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 获取请求参数，封装到 course 对象中
            Course course = JsonUtils.parseJSON2Object(request, Course.class);

            // 补全未添加部分字段
            // 1. createDate 字段
            course.setCreateDate(DateUtils.parseDate2String(new Date()));
            // 2. icon
            course.setIcon(null);
            // 3. userId
            User user = (User) request.getSession().getAttribute(Constants.LOGIN_USER);
            course.setUserId(user.getId());
            // 4. orderNo
            course.setOrderNo(1);

            // 调用 业务层 添加 学科
            courseService.add(course);

            // 添加成功，响应
            JsonUtils.printResult(response, new Result(true, "添加学科成功"));
        } catch (Exception e) {
            e.printStackTrace();
            // 添加失败
            JsonUtils.printResult(response, new Result(false, "添加学科失败"));
        }

    }

    /**
     * 分页查询学科
     */
    @RequestMapping("/course/findByPage")
    public void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 获取请求参数，存储到 queryPageBean 对象中
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(request, QueryPageBean.class);

            // 调用 业务层，分页查询，返回 pageResult 对象
            PageResult pageResult = courseService.findByPage(queryPageBean);

            // 查询成功，响应
            JsonUtils.printResult(response, new Result(true, "分页查询学科成功", pageResult));

        } catch (Exception e) {
            e.printStackTrace();
            // 查询失败
            JsonUtils.printResult(response, new Result(false, "分页查询学科失败"));
        }

    }

    /**
     * 更新学科信息
     */
    @RequestMapping("/course/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 获取请求参数，封装到 course 对象中
            Course course = JsonUtils.parseJSON2Object(request, Course.class);

            // 调用 业务层，更新学科信息
            courseService.update(course);

            // 更新成功，响应
            JsonUtils.printResult(response, new Result(true, "更新学科成功"));
        } catch (Exception e) {
            e.printStackTrace();
            // 更新失败
            JsonUtils.printResult(response, new Result(false, "更新学科失败"));
        }

    }


}
