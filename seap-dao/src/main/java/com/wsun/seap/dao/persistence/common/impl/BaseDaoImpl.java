package com.wsun.seap.dao.persistence.common.impl;

import com.wsun.seap.dao.persistence.common.BaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/10/5 0005.
 */
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

    private static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public boolean add(String classMethod, T entity) {
        boolean flag = false;
        try {
            flag = this.getSqlSession().insert(classMethod, entity) > 0 ? true : false;
        } catch (Exception e) {
            flag = false;
            logger.error(e.getMessage());
        }
        return flag;
    }

    public boolean edit(String classMethod, T entity) {
        boolean flag = false;
        try {
            flag = this.getSqlSession().update(classMethod, entity) > 0 ? true : false;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public T get(String classMethod, T entity) {
        T result = null;
        try {
            result = (T) this.getSqlSession().selectOne(classMethod, entity);
        } catch (Exception e) {
        }
        return result;
    }

    public List<T> getAll(String classMethod, Object paramObj) {
        List<T> result = new ArrayList<T>();
        try {
            result = this.getSqlSession().selectList(classMethod, paramObj);
        } catch (Exception e) {
        }
        return result;
    }

    public boolean remove(String classMethod, T entity) {
        boolean flag = false;
        try {
            flag = this.getSqlSession().delete(classMethod, entity) > 0 ? true : false;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

}
