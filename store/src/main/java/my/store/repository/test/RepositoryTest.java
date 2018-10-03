package my.store.repository.test;

import my.store.application.model.*;
import my.store.application.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class RepositoryTest {

    @Autowired
    private static RoleService roleService;
    @Autowired
    private static UserService userService;
    @Autowired
    private static ProductService productService;
    @Autowired
    private static CustomerService customerService;
    @Autowired
    private static OrderService orderService;

    private static void addRoles(Role... roles) {
        for (Role role : roles) {
            roleService.createRole(role);
        }
    }

    private static void addUsers(User... users) {
        for (User user : users) {
            userService.createUser(user);
        }
    }

    private static void addProducts(Product... products) {
        for (Product product : products) {
            productService.createProduct(product);
        }
    }

    private static void addCustomer(Customer... customers) {
        for (Customer customer : customers) {
            customerService.createCustomer(customer);
        }
    }

    private static void addOrders(Order... orders) {
        for (Order order : orders) {
            orderService.createOrder(order);
        }
    }

    public static void main(String[] args) {
        Role admin = new Role("admin");
        Role user = new Role("user");
        addRoles(admin, user);

        User administrator = new User("admin", "admin", admin);
        User userator = new User("user", "user", user);
        addUsers(administrator, userator);

        Product bread = new Product("Bread", 12, "https://www.browneyedbaker.com/wp-content/uploads/2016/05/white-bread-51-600-600x400.jpg");
        Product eggs = new Product("Eggs", 25, "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/articles/health_tools/eggs_slideshow/getty_photo_of_eggs_in_carton.jpg");
        Product milk = new Product("Milk", 23, "https://images.milkandmore.co.uk/image/upload/w_iw/f_auto/w_300,h_300,d_back_up_image.jpg/v1/products/2004541_1.jpg");
        addProducts(bread, eggs, milk);

        Customer customer = new Customer("TestCustomer", "TestCustomer@gmail.com");
        addCustomer(customer);

        Order order = new Order(Arrays.asList(bread, milk), customer);
        addOrders(order);
    }
}
