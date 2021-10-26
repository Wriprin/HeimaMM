package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entry.Result;
import com.itheima.mm.pojo.WxMember;
import com.itheima.mm.service.QuestionService;
import com.itheima.mm.service.WxMemberService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: wriprin
 * @Date: 2021/10/24/024 20:58:07
 * @Version 1.0
 */
@Controller
public class QuestionController {
    private WxMemberService wxMemberService = new WxMemberService();
    private QuestionService questionService = new QuestionService();
    /**
     * 查询题库分类列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/questions/categories")
    public void categories(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 通过 请求头 "Authorization" 获取 wxMemberId
            Integer id = Integer.valueOf((request.getHeader("Authorization")).substring(7));

            // 调用 wxMemberService 业务层，通过 id 获取用户信息
            WxMember wxMember = wxMemberService.findById(id);

            // 获取请求参数，封装到 map 对象中
            Map parameterMap = JsonUtils.parseJSON2Object(request, Map.class);
            // 将 wxMember 信息添加到 parameterMap 中
            parameterMap.put("memberId", wxMember.getId());
            parameterMap.put("courseId", wxMember.getCourseId());
            parameterMap.put("cityId", wxMember.getCityId());

            // 调用 业务层，查询学科种类列表
            List<Map> categoriesList = questionService.findCategories(parameterMap);

            // 查询成功
            JsonUtils.printResult(response, new Result(true, "查询学科种类列表成功", categoriesList));
        } catch (Exception e) {
            e.printStackTrace();
            // 查询失败
            JsonUtils.printResult(response, new Result(false, "查询学科种类列表失败"));
        }
    }

}
