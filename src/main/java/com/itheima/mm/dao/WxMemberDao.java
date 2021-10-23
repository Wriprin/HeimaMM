package com.itheima.mm.dao;

import com.itheima.mm.pojo.WxMember;

/**
 * @Author: wriprin
 * @Date: 2021/10/23/023 17:03:37
 * @Version 1.0
 */
public interface WxMemberDao {
    /**
     * 根据 会员昵称 查找 会员信息
     * @param nickName
     * @return
     */
    WxMember findByNickName(String nickName);

    /**
     * 添加会员
     * @param wxMember
     */
    void addWxMember(WxMember wxMember);
}
