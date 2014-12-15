package com.wsun.seap.dao.persistence.orm;


import com.wsun.seap.domain.po.User;

import java.util.List;

/**
 * Created by Administrator on 2014/10/5 0005.
 */
public interface UserDao {

    public List<User> queryUserList(Page page);

    public List<User> queryUserList();
}
