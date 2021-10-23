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

    /**
     * 通过 id 查询对应的 wxMember
     * @param id
     * @return
     */
    WxMember findById(Integer id);

    /**
     * 更新 wxMember
     * @param wxMember
     */
    void update(WxMember wxMember);
}
