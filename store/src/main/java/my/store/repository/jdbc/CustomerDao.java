package my.store.repository.jdbc;

import my.store.application.model.Customer;
import my.store.repository.generic.GenericDao;

import java.util.List;

public interface CustomerDao extends GenericDao<Integer, Customer> {

    List<Customer> getByName(String name);

    Customer getByEmail(String email);

}
