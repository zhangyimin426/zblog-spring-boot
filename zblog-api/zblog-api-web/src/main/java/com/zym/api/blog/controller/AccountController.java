package com.zym.api.blog.controller;

import com.zym.common.base.form.AccountForm;
import com.zym.common.base.model.Account;
import com.zym.common.base.service.AccountService;
import com.zym.common.base.statuscode.GlobalResultStatus;
import com.zym.common.base.utils.DateUtil;
import com.zym.common.base.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Gavin
 * @date 2016-09-29
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public Object getById(@PathVariable Integer accountId) {
        Account account = accountService.get(accountId);
        if (account == null) {
            return JsonResult.fail(GlobalResultStatus.PARAM_MISSING);
        }
        return JsonResult.success(account);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object getNaviPage(AccountForm form, Integer page, Integer perPage) {
        if (form == null) {
            return JsonResult.fail(GlobalResultStatus.PARAM_MISSING);
        }
        if (page == null) {
            page = 1;
        }
        if (perPage == null) {
            perPage = 4;
        }
        return accountService.getByForm(form, page, perPage);

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object add(Account account) {
        account.setCreateTime(DateUtil.getLocalDate(new Date()));
        account.setStatus(1);
        accountService.insert(account);
        return JsonResult.success();
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.PUT)
    public Object update(@PathVariable int accountId, Account account) {
        if (accountId <= 0) {
            return JsonResult.fail(GlobalResultStatus.PARAM_MISSING);
        }
        account.setUpdateTime(DateUtil.getLocalDate(new Date()));
        accountService.update(account);
        return JsonResult.success();
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable Integer accountId) {
        if (accountId <= 0) {
            return JsonResult.fail(GlobalResultStatus.PARAM_MISSING);
        }
        accountService.delete(accountId);
        return JsonResult.success();
    }
}
