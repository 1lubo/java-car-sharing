package carsharing.storage.dao;

import carsharing.storage.DbClient;
import carsharing.storedObjects.customer.Customer;
import carsharing.storedObjects.customer.CustomerDao;

import java.util.List;

public class DbCustomerDao implements CustomerDao {

    private static final String SELECT_ALL = "SELECT * FROM CUSTOMER";
    private static final String INSERT = "INSERT INTO CUSTOMER(NAME) VALUES ('%s')";
    private static final String RETURN_CAR = "UPDATE CUSTOMER SET RENTED_CAR_ID = null WHERE id = %d";
    private static final String RENT_CAR = "UPDATE CUSTOMER SET RENTED_CAR_ID = %d WHERE id = %d";

    private final DbClient dbClient;
    public DbCustomerDao(DbClient dbClient) {
        this.dbClient = dbClient;
    }
    @Override
    public List<Customer> findAll() {
        return dbClient.selectCustomersForList(SELECT_ALL);
    }

    @Override
    public void add(Customer customer) {
        dbClient.run(String.format(INSERT, customer.getName()));
    }

    @Override
    public boolean isEmpty() {
        return dbClient.selectCustomersForList(SELECT_ALL).isEmpty();
    }

    public void returnCar(Customer customer) { dbClient.run(String.format(RETURN_CAR, customer.getId())); }
    public void rentCar(Customer customer) { dbClient.run(String.format(RENT_CAR, customer.getRentedCarId(), customer.getId())); }
}
