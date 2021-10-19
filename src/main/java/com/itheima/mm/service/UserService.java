package com.itheima.mm.service;

import com.itheima.mm.dao.UserDao;
import com.itheima.mm.pojo.User;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * @Author: wriprin
 * @Date: 2021/10/19/019 7:49:51
 * @Version 1.0
 */
public class UserService {

    /**
     * 后台用户登录校验
     * @param user
     * @return
     */
    public User login(User user) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        // 调用 dao 层，通过 username 查询查找 user
        User loginUser = userDao.findByUsername(user.getUsername());
        SqlSessionFactoryUtils.commitAndClose(sqlSession);

        if (loginUser != null) {
            // 用户名存在，校验密码是否正确
            if (loginUser.getPassword().equals(user.getPassword())) {
                // 密码正确
                return loginUser;
            } else {
                // 密码错误
                throw new RuntimeException("密码错误");
            }
        } else {
            // 用户名不存在
            throw new RuntimeException("用户名不存在");
        }
    }
}
