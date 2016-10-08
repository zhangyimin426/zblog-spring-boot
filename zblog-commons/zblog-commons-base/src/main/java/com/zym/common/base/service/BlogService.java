package com.zym.common.base.service;

import com.zym.common.base.model.Blog;
import com.zym.common.base.service.base.BaseService;

import java.util.List;

/**
 * @author Gavin
 * @date 2016-09-05
 */
public interface BlogService extends BaseService<Blog> {

    /**
     * 根据博客id获取详情
     * @param id
     * @return
     */
    Blog getById(int id);

    List<Blog> getAll(Integer page, Integer perPage);
}
