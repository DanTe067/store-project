package my.store.application.service;

import my.store.application.model.Order;
import my.store.repository.hibernate.OrderDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDaoImpl orderDao;

    public void createOrder(Order order) {
        orderDao.create(order);
    }

    public Order getOrder(Integer id) {
        return orderDao.get(id);
    }

    public Order updateOrder(Order order) {
        return orderDao.update(order);
    }

    public void deleteOrder(Integer id) {
        orderDao.delete(id);
    }

    public List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    public List<Order> getOrdersByCustomerId(int id) {
        return orderDao.getByCustomerId(id);
    }
}
