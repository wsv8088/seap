package com.wsun.seap.dao.persistence.orm;


import com.wsun.seap.dao.persistence.annatation.MyBatisRepository;
import com.wsun.seap.domain.po.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by Administrator on 2014/10/5 0005.
 */
@MyBatisRepository
public interface UserDao<T> {
	public List<T> queryUserList (User user, Page page);
	public T queryUser (T param);
}
