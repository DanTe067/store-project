package my.store.repository.jdbc;

import my.store.application.model.Order;
import my.store.repository.generic.GenericDao;

import java.util.List;

public interface OrderDao extends GenericDao<Integer, Order> {

    List<Order> getByCustomerId(int id);

}
