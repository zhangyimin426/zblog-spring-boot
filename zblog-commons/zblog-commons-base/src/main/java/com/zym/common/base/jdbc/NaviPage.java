package com.zym.common.base.jdbc;



import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author gavin
 * @date 2016-09-28
 */
public class NaviPage<T> implements Serializable {


    private List<T> list;

    private Page pager;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Page getPager() {
        return pager;
    }

    public void setPager(Page pager) {
        this.pager = pager;
    }
}
