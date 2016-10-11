package com.zym.business.blog.service;

import com.zym.business.blog.dao.BaseDao;
import com.zym.business.blog.dao.UserDao;
import com.zym.business.blog.service.base.BaseServiceImpl;
import com.zym.common.base.model.User;
import com.zym.common.base.model.UserInfo;
import com.zym.common.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gavin
 * @date 2016-10-10
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public BaseDao<User> getDao() {
        return userDao;
    }

    @Override
    public User getUserByAccount(Integer accountId) {
        return userDao.getUserByAccount(accountId);
    }

    @Override
    public UserInfo getUserInfoByUserId(Integer userId) {
        return userDao.getUserInfoByUserId(userId);
    }

    @Override
    public UserInfo getUserInfoByAccountId(Integer accountId) {
        return userDao.getUserInfoByAccountId(accountId);
    }

    @Override
    public UserInfo getUserInfoByAccount(String account) {
        return userDao.getUserInfoByAccount(account);
    }
}
