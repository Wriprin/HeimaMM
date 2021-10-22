package com.itheima.mm.service;

import com.alibaba.fastjson.JSON;
import com.itheima.mm.dao.CityDao;
import com.itheima.mm.pojo.Dict;
import com.itheima.mm.utils.LocationUtil;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wriprin
 * @Date: 2021/10/22/022 19:02:03
 * @Version 1.0
 */
public class CityService {
    /**
     * 根据城市名查询城市信息
     * @param parameterMap
     * @return
     */
    public Map findByCityName(Map parameterMap) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CityDao cityDao = sqlSession.getMapper(CityDao.class);

        // 调用 高德 API，通过逆地理编码查询出用户所在城市
        String location = (String) parameterMap.get("location");
        String cityName = LocationUtil.parseLocation(location);
        Dict currentCity = cityDao.findByCityName(cityName);

        String fs = null;
        if (parameterMap.get("fs") instanceof Integer) {
            fs = (Integer) parameterMap.get("fs") + "";
        } else {
            fs = (String) parameterMap.get("fs");
        }
        List<Dict> cityList = cityDao.findCityList(fs);

        // 将 当前城市信息，和要查询的城市列表存放到 map 对象中
        Map resultMap = new HashMap();
        resultMap.put("location", currentCity);
        resultMap.put("cities", cityList);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return resultMap;
    }
}
