package my.flipside.repository.test;

import my.flipside.application.config.AppConfig;
import my.flipside.repository.dao.hibernate.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RepositoryTest {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        RoleDao roleDao = context.getBean("roleDao", RoleDaoImpl.class);
        StatDao statDao = context.getBean("statDao", StatDaoImpl.class);
        UserDao userDao = context.getBean("userDao", UserDaoImpl.class);
        GameDao gameDao = context.getBean("gameDao", GameDaoImpl.class);
        ResultDao resultDao = context.getBean("resultDao", ResultDaoImpl.class);

        //.................
    }
}
