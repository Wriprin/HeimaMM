package com.itheima.mm.dao;

import com.itheima.mm.pojo.User;

/**
 * @Author: wriprin
 * @Date: 2021/10/19/019 7:54:38
 * @Version 1.0
 */
public interface UserDao {
    User findByUsername(String username);
}
