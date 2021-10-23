package com.itheima.mm.service;

import com.itheima.mm.dao.WxMemberDao;
import com.itheima.mm.pojo.WxMember;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * @Author: wriprin
 * @Date: 2021/10/23/023 16:55:18
 * @Version 1.0
 */
public class WxMemberService {

    /**
     * 根据 会员昵称 查询 会员信息
     * @param nickName
     * @return
     */
    public WxMember findByNickName(String nickName) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao wxMemberDao = sqlSession.getMapper(WxMemberDao.class);

        WxMember loginMember = wxMemberDao.findByNickName(nickName);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return loginMember;
    }

    /**
     * 会员注册
     * @param wxMember
     */
    public void addWxMember(WxMember wxMember) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao wxMemberDao = sqlSession.getMapper(WxMemberDao.class);

        wxMemberDao.addWxMember(wxMember);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }

    /**
     * 通过 id 查询对应的 wxMember
     * @param id
     * @return
     */
    public WxMember findById(Integer id) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao wxMemberDao = sqlSession.getMapper(WxMemberDao.class);

        WxMember wxMember = wxMemberDao.findById(id);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return wxMember;
    }

    /**
     * 更新 wxMember
     * @param wxMember
     */
    public void update(WxMember wxMember) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao wxMemberDao = sqlSession.getMapper(WxMemberDao.class);

        wxMemberDao.update(wxMember);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }
}
