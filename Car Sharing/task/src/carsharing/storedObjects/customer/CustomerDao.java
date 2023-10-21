package carsharing.storedObjects.customer;

import java.util.List;

public interface CustomerDao {
        List<Customer> findAll();
        void add(Customer customer);
        boolean isEmpty();
}
