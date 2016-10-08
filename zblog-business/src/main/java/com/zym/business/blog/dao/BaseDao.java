package com.zym.business.blog.dao;

import com.github.pagehelper.Page;
import com.zym.common.base.jdbc.OrderByDto;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * 数据库操作基类，如果需要调用下列方法，需要在对应的mapper.xml配置
 *
 * @author gavin
 */
public interface BaseDao<T> {
    /**
     * 插入
     *
     * @param t 插入的对象
     * @return 插入数据条数
     */
    int insert(T t);

    /**
     * 批量插入
     *
     * @param list 数据列表
     * @return 插入数据条数
     */
    int insertList(List<T> list);

    /**
     * 修改
     *
     * @param t 修改的数据
     * @return 修改的数据条数
     */
    int update(T t);

    /**
     * 删除
     *
     * @param id 数据标识
     * @return 删除的数据条数
     */
    int delete(Serializable id);

    /**
     * 通过条件查询记录数
     *
     * @param t 查询条件
     * @return 记录数
     */
    int getTotal(T t);

    /**
     * 通过条件查询数据列表
     *
     * @param t 查询条件
     * @return 数据列表
     */
    List<T> getList(T t);

    /**
     * 查询所有数据
     *
     * @return 数据列表
     */
    List<T> getAll();

    /**
     * 通过id查询数据
     *
     * @param id 数据标识
     * @return 数据对象
     */
    T get(Serializable id);

    /**
     * 根据条件分页查询
     *
     * @param t       查询参数
     * @param orderBy 排序
     * @param pager   分页
     * @return 分页数据列表
     */
    List<T> getPage(@Param("queryParam") T t, @Param("orderBy") OrderByDto orderBy, @Param("pager") Page pager);

    /**
     * 根据条件分页查询，添加过滤器则带分页参数
     *
     * @param t
     * @param pager
     * @return
     */
    List<T> getNaviPager(@Param("param") T t, @Param("orderBy") OrderByDto orderBy, @Param("pager") Page pager);


    List<T> getNaviPage(@Param("param") T t, @Param("pager") Page pager);


    List<T> getNaviPage(@Param("pager") Page pager);

}
