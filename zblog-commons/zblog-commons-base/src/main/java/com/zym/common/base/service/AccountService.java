package com.zym.common.base.service;

import com.zym.common.base.model.Account;
import com.zym.common.base.service.base.BaseService;

/**
 * @author Gavin
 * @date 2016-09-28
 */
public interface AccountService extends BaseService<Account> {

    /**
     * 通过帐号获取用户信息
     *
     * @param account 帐号：手机号码或邮箱
     * @return 帐号信息
     */
    Account getAccount(String account);

}
