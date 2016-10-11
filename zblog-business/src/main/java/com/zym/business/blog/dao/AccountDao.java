package com.zym.business.blog.dao;

import com.zym.common.base.form.AccountForm;
import com.zym.common.base.model.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountDao extends BaseDao<Account> {

    int deleteByPrimaryKey(Integer accountId);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    Account getByAccount(Account account);

    List<Account> getByForm(AccountForm form);

}