package com.zym.common.base.service;

import com.zym.common.base.model.AppInfo;

/**
 * @author Gavin
 * @date 2016-10-09
 */
public interface AppInfoService  {

    /**
     * 根据客户端属于获取appInfo
     * @param belongs 0-H5，1-Android，2-IOS
     * @return
     */
    AppInfo getByBelongs(Integer belongs);

    /**
     * 根据appKey获取appSecret
     * @param appKey
     * @return
     */
    AppInfo getByAppKey(String appKey);

}
