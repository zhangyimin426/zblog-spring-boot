package com.zym.business.blog.dao;

import com.zym.common.base.model.User;

public interface UserDao extends BaseDao<User> {

    int deleteByPrimaryKey(Integer userId);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

}