package com.wsun.seap.service.system;

import com.wsun.seap.domain.po.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dbwangshuang on 2014/12/16.
 */
public interface UserService {
	public List<User> queryPageUserList(User user);

	public List<User> queryUserList(User user);

	public User queryUser(User user);
}
