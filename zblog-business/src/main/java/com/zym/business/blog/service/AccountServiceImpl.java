package com.zym.business.blog.service;

import com.github.pagehelper.PageHelper;
import com.zym.business.blog.dao.AccountDao;
import com.zym.business.blog.dao.BaseDao;
import com.zym.business.blog.service.base.BaseServiceImpl;
import com.zym.common.base.form.AccountForm;
import com.zym.common.base.model.Account;
import com.zym.common.base.service.AccountService;
import com.zym.common.base.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Gavin
 * @date 2016-10-10
 */
@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account getByAccount(String account) {
        Account user = new Account();
        if (StringUtil.isInt(account)) {
            user.setPhone(account);
            return accountDao.getByAccount(user);
        } else if (StringUtil.isEmail(account)) {
            user.setEmail(account);
            return accountDao.getByAccount(user);
        }
        return null;
    }

    @Override
    public List<Account> getByForm(AccountForm form, Integer page, Integer perPage) {
        PageHelper.startPage(page, perPage);
        return accountDao.getByForm(form);
    }

    @Override
    public BaseDao<Account> getDao() {
        return accountDao;
    }
}
