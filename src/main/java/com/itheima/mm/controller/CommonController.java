package com.itheima.mm.controller;

import com.alibaba.fastjson.JSON;
import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entry.Result;
import com.itheima.mm.pojo.Dict;
import com.itheima.mm.service.CityService;
import com.itheima.mm.utils.JsonUtils;
import com.itheima.mm.utils.LocationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wriprin
 * @Date: 2021/10/22/022 18:35:52
 * @Version 1.0
 */

@Controller
public class CommonController {
    private CityService cityService = new CityService();

    /**
     * 获取城市
     */
    @RequestMapping("/common/cities")
    public void cities(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 获取请求参数，封装到 map 对象中
            Map parameterMap = JsonUtils.parseJSON2Object(request, Map.class);
            // 调用 业务层，将获取到的城市从 数据库中 查询出对应的 城市信息
            Map resultMap = cityService.findByCityName(parameterMap);
            // 查询成功
            JsonUtils.printResult(response, new Result(true, "城市 获取成功", resultMap));
        } catch (Exception e) {
            e.printStackTrace();
            // 查询失败
            JsonUtils.printResult(response, new Result(false, "城市 获取失败"));
        }
    }

}
