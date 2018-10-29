package my.store.repository.test;

import my.store.application.config.AppConfig;
import my.store.application.model.*;
import my.store.application.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepositoreCheck {

    private static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    private static RoleService roleService = context.getBean("roleService", RoleService.class);
    private static UserService userService = context.getBean("userService", UserService.class);
    private static ProductService productService = context.getBean("productService", ProductService.class);
    private static CustomerService customerService = context.getBean("customerService", CustomerService.class);
    private static OrderService orderService = context.getBean("orderService", OrderService.class);

    public static void main(String[] args) {
        List<Role> roles = roleService.getAllRoles();
        for (Role role : roles) {
            System.out.println(role);
        }

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }

        List<Customer> customers = customerService.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }

        List<Order> orders = orderService.getAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
