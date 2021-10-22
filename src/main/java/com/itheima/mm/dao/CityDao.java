package com.itheima.mm.dao;

import com.itheima.mm.pojo.Dict;

import java.util.List;

/**
 * @Author: wriprin
 * @Date: 2021/10/22/022 19:07:33
 * @Version 1.0
 */
public interface CityDao {
    /**
     * 通过 城市名 查询 城市信息
     * @param cityName
     * @return
     */
    Dict findByCityName(String cityName);

    /**
     * 通过 fs 的值查询城市（fs = 0 查询所有城市，fs = 1 查询热门城市）
     * @param fs
     * @return
     */
    List<Dict> findCityList(String fs);
}
