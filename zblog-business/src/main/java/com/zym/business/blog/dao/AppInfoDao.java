package com.zym.business.blog.dao;

import com.zym.common.base.model.AppInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Gavin
 * @date 2016-10-10
 */
@Mapper
public interface AppInfoDao extends BaseDao<AppInfo> {
    /**
     * 根据客户端属于获取appInfo
     *
     * @param belongs 0-H5，1-Android，2-IOS
     * @return
     */
    AppInfo getByBelongs(Integer belongs);

    /**
     * 根据appKey获取appSecret
     *
     * @param appKey
     * @return
     */
    AppInfo getByAppKey(String appKey);
}
