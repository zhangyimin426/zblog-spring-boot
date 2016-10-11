package com.zym.business.blog.dao;

import com.zym.common.base.model.User;
import com.zym.common.base.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseDao<User> {

    int deleteByPrimaryKey(Integer userId);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    User getUserByAccount(Integer accountId);

    UserInfo getUserInfoByUserId(Integer userId);

    UserInfo getUserInfoByAccountId(Integer accountId);

    UserInfo getUserInfoByAccount(String account);

}