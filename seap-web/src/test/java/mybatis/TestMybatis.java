package mybatis;

import com.wsun.seap.dao.persistence.orm.Page;
import com.wsun.seap.dao.persistence.orm.UserDao;
import com.wsun.seap.domain.po.User;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.List;

/**
 * Created by Administrator on 2014/10/5 0005.
 */

public class TestMybatis {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");

    }
}
