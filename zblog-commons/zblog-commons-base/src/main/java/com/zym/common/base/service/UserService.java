package com.zym.common.base.service;

import com.zym.common.base.model.User;
import com.zym.common.base.model.UserInfo;
import com.zym.common.base.service.base.BaseService;

/**
 * @author Gavin
 * @date 2016-09-29
 */
public interface UserService extends BaseService<User> {

    /**
     * 根据账号id获取用户基本信息
     *
     * @param accountId
     * @return
     */
    User getUserByAccount(Integer accountId);

    /**
     * 根据userId获取用户全部信息
     *
     * @param userId
     * @return
     */
    UserInfo getUserInfoByUserId(Integer userId);

    /**
     * 根据accountId获取用户全部信息
     *
     * @param accountId
     * @return
     */
    UserInfo getUserInfoByAccountId(Integer accountId);

    /**
     * 通过帐号获取用户全部信息
     *
     * @param account 帐号：手机号码或邮箱
     * @return 用户详细信息
     */
    UserInfo getUserInfoByAccount(String account);
}
