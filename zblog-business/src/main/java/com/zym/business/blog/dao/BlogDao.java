package com.zym.business.blog.dao;

import com.zym.common.base.model.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Gavin
 * @date 2016-09-05
 */
@Mapper
public interface BlogDao extends BaseDao<Blog> {

    Blog getById(int id);

    List<Blog> getAll();
}
