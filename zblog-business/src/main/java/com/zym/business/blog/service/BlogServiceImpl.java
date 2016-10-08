package com.zym.business.blog.service;

import com.github.pagehelper.PageHelper;
import com.zym.business.blog.dao.BaseDao;
import com.zym.business.blog.dao.BlogDao;
import com.zym.business.blog.service.base.BaseServiceImpl;
import com.zym.common.base.model.Blog;
import com.zym.common.base.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Gavin
 * @date 2016-09-28
 */
@Service("blogService")
public class BlogServiceImpl extends BaseServiceImpl<Blog> implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getById(int id) {
        return blogDao.getById(id);
    }

    @Override
    public List<Blog> getAll(Integer page, Integer perPage) {
        PageHelper.startPage(page, perPage);
        return blogDao.getAll();
    }
    @Override
    public BaseDao<Blog> getDao() {
        return blogDao;
    }
}
