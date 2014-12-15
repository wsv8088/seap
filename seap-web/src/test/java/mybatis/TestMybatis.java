package mybatis;

import com.wsun.seap.dao.persistence.orm.Page;
import com.wsun.seap.dao.persistence.orm.UserDao;
import com.wsun.seap.domain.po.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Administrator on 2014/10/5 0005.
 */

public class TestMybatis {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");

        UserDao userDao = (UserDao) ctx.getBean("userDao");

        System.out.println(userDao);

        List<User> users = userDao.queryUserList(new Page<User>());

        System.out.println(users.size());
        for (User user : users) {
            System.out.println(user.getId() + "====>" + user.getName());
        }

        List<User> us = userDao.queryUserList();
        System.out.println(us.size());
    }
}
