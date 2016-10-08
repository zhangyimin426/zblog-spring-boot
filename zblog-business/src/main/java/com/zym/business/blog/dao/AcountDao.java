package com.zym.business.blog.dao;

import com.zym.common.base.model.Account;

public interface AcountDao extends BaseDao<Account> {

    int deleteByPrimaryKey(Integer accountId);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

}