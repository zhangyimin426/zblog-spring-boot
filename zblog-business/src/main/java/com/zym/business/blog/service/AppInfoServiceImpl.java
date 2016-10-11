package com.zym.business.blog.service;

import com.zym.business.blog.dao.AppInfoDao;
import com.zym.business.blog.dao.BaseDao;
import com.zym.business.blog.service.base.BaseServiceImpl;
import com.zym.common.base.model.AppInfo;
import com.zym.common.base.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gavin
 * @date 2016-10-10
 */
@Service("appInfoService")
public class AppInfoServiceImpl extends BaseServiceImpl<AppInfo> implements AppInfoService {

    @Autowired
    private AppInfoDao appInfoDao;

    @Override
    public AppInfo getByBelongs(Integer belongs) {
        return appInfoDao.getByBelongs(belongs);
    }

    @Override
    public AppInfo getByAppKey(String appKey) {
        return appInfoDao.getByAppKey(appKey);
    }

    @Override
    public BaseDao<AppInfo> getDao() {
        return appInfoDao;
    }
}
