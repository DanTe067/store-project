package my.store.application.service;

import my.store.application.model.Customer;
import my.store.repository.hibernate.CustomerDaoHibernateImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerService {

    @Autowired
    private CustomerDaoHibernateImpl customerDao;

    public void createCustomer(Customer customer) {
        customerDao.create(customer);
    }

    public Customer getCustomer(Integer id) {
        return customerDao.get(id);
    }

    public Customer updateCustomer(Customer customer) {
        return customerDao.update(customer);
    }

    public void deleteCustomer(Integer id) {
        customerDao.delete(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAll();
    }

    public List<Customer> getCustomersByName(String name) {
        return customerDao.getByName(name);
    }

    public Customer getCustomerByEmail(String email) {
        return customerDao.getByEmail(email);
    }
}
