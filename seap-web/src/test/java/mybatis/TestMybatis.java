package mybatis;

import com.wsun.seap.common.context.QueryParam;
import com.wsun.seap.dao.orm.UserDao;
import com.wsun.seap.domain.po.system.User;
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
        QueryParam qp = new QueryParam();
        qp.addParam("loginName", "ws");
        qp.addParam("rows", 10);

        List<User> pageUsers = userDao.queryAllUsers(qp);

        System.out.println(pageUsers.size());
    }
}
