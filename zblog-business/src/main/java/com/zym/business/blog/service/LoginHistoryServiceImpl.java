package com.zym.business.blog.service;

import com.zym.business.blog.dao.BaseDao;
import com.zym.business.blog.dao.LoginHistoryDao;
import com.zym.business.blog.service.base.BaseServiceImpl;
import com.zym.common.base.model.LoginHistory;
import com.zym.common.base.service.LoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gavin
 * @date 2016-10-10
 */
@Service("loginHistoryService")
public class LoginHistoryServiceImpl extends BaseServiceImpl<LoginHistory> implements LoginHistoryService {

    @Autowired
    private LoginHistoryDao loginHistoryDao;
    @Override
    public BaseDao<LoginHistory> getDao() {
        return loginHistoryDao;
    }
}
