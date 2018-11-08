package my.flipside.repository.test;

import my.flipside.application.config.AppConfig;
import my.flipside.application.model.FlipUser;
import my.flipside.application.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RepositoryTest {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        RoleService roleService = context.getBean("roleService", RoleService.class);
        StatService statService = context.getBean("statService", StatService.class);
        UserService userService = context.getBean("userService", UserService.class);
        GameService gameService = context.getBean("gameService", GameService.class);
        ResultService resultService = context.getBean("resultService", ResultService.class);

        for (FlipUser flipUser : userService.getAllUsers()) {
            System.out.println(flipUser);
        }
        //.................
    }
}
