package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.constants.Constants;
import com.itheima.mm.entry.Result;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.UserService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: wriprin
 * @Date: 2021/10/19/019 7:47:29
 * @Version 1.0
 */
@Controller
public class UserController {
    private UserService userService = new UserService();

    /**
     * 后台用户登录
     */
    @RequestMapping("/user/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 获取请求参数，封装到 user 对象中
            User user = JsonUtils.parseJSON2Object(request, User.class);

            // 调用 业务层，校验用户名&密码
            User loginUser = userService.login(user);

            // 校验成功返回 loginUser 对象，存储到 session 中
            request.getSession().setAttribute(Constants.LOGIN_USER, loginUser);

            JsonUtils.printResult(response, new Result(true, "登录成功", loginUser));

        } catch (Exception e) {
            e.printStackTrace();
            // 校验&登录失败
            JsonUtils.printResult(response, new Result(false, e.getMessage()));
        }
    }

    /**
     * 后台用户退出登录
     */
    @RequestMapping("/user/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 销毁 session
        request.getSession().invalidate();

        JsonUtils.printResult(response, new Result(true, "退出成功"));
    }

}
