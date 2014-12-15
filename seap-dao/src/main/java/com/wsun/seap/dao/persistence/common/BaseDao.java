package com.wsun.seap.dao.persistence.common;

import java.util.List;

/**
 * Created by Administrator on 2014/10/5 0005.
 */
public interface BaseDao<T> {
    public boolean add(String classMethod, T entity);

    public boolean edit(String classMethod, T entity);

    public boolean remove(String classMethod, T entity);

    public T get(String classMethod, T entity);

    public List<T> getAll(String classMethod, Object paramObj);

}
