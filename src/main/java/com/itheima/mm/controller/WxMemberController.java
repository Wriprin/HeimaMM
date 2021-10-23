package com.itheima.mm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entry.Result;
import com.itheima.mm.pojo.WxMember;
import com.itheima.mm.service.WxMemberService;
import com.itheima.mm.utils.DateUtils;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wriprin
 * @Date: 2021/10/23/023 9:11:28
 * @Version 1.0
 */

@Controller
public class WxMemberController {
    private WxMemberService wxMemberService = new WxMemberService();

    /**
     * 微信用户注册登录
     */
    @RequestMapping("/wxMember/login")
    public void demo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 获取请求参数，封装到 wxMember 对象中
            WxMember wxMember = JsonUtils.parseJSON2Object(request, WxMember.class);

            // 获取 wxMember 的 nickName，从数据库中查找对应 wxMember 对象
            WxMember loginMember = wxMemberService.findByNickName(wxMember.getNickName());

            // 判断 loginMember 是否为 Null
            if (loginMember != null) {
                // 不为 null, 说明该用户已经注册过，那么直接登录就可以了
                // ...
            } else {
                // 为 Null, 说明该用户未注册过，那么就调用 业务层，注册用户
                // 补全 createTime
                wxMember.setCreateTime(DateUtils.parseDate2String(new Date()));
                wxMemberService.addWxMember(wxMember);
                loginMember = wxMemberService.findByNickName(wxMember.getNickName());
            }

            // 返回数据：token, userInfo
            Map resultMap = new HashMap();
            resultMap.put("token", loginMember.getId());
            resultMap.put("userInfo", loginMember);

            JsonUtils.printResult(response, new Result(true, "登录成功", resultMap));
        } catch (Exception e) {
            e.printStackTrace();
            // 失败
            JsonUtils.printResult(response, new Result(false, "登录失败"));
        }


    }
}
