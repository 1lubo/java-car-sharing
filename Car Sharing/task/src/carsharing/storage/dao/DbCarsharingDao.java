package carsharing.storage.dao;

import carsharing.storage.DbClient;
import carsharing.storedObjects.car.Car;
import carsharing.storedObjects.company.Company;
import carsharing.storedObjects.customer.Customer;

import java.util.Arrays;
import java.util.List;

public class DbCarsharingDao {
    static final String CREATE_COMPANY_TABLE =
            "CREATE TABLE IF NOT EXISTS COMPANY (ID INTEGER AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(25) NOT NULL UNIQUE)";
    static final String CREATE_CAR_TABLE = "CREATE TABLE IF NOT EXISTS CAR (" +
            "ID INTEGER AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(25) NOT NULL UNIQUE, COMPANY_ID INTEGER NOT NULL," +
            "CONSTRAINT FK_COMPANY FOREIGN KEY (COMPANY_ID)" +
            "REFERENCES COMPANY(ID) ON DELETE CASCADE)";

    static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE IF NOT EXISTS CUSTOMER (" +
    "ID INTEGER AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50) NOT NULL UNIQUE, RENTED_CAR_ID INTEGER DEFAULT NULL," +
    "FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR (ID) ON DELETE SET NULL NOCHECK)";
    static final String DB_URL = "jdbc:h2:./src/carsharing/db/";
    private final DbClient dbClient;
    private final DbCompanyDao companyDao;
    private final DbCarDao carDao;
    private final DbCustomerDao customerDao;

    public DbCarsharingDao(String [] args) {
        dbClient = new DbClient(dbUrlFromArgs(args));
        dbClient.run(CREATE_COMPANY_TABLE);
        dbClient.run(CREATE_CAR_TABLE);
        dbClient.run(CREATE_CUSTOMER_TABLE);
        companyDao = new DbCompanyDao(dbClient);
        carDao = new DbCarDao(dbClient);
        customerDao = new DbCustomerDao(dbClient);
    }

    public List<Company> findAllCompanies() {
        return companyDao.findAll();
    }

    public List<Customer> findAllCustomers() { return customerDao.findAll();}

    public List<Car> findCarsByCompanyId(int company_id) {
        return carDao.selectCarsByCompany(company_id);
    }
    public Car findCarById(int carId) { return carDao.findById(carId); }
    public Company findCompanyById(int companyId) { return companyDao.findById(companyId); }

    public void add(Company company) {
        companyDao.add(company);
    }
    public void add(Car car) {
        carDao.add(car);
    }
    public void add(Customer customer) { customerDao.add(customer); }

    public boolean noCompanies() {
        return companyDao.isEmpty();
    }

    public boolean noCustomers() { return customerDao.isEmpty(); }
    public void returnRentedCar(Customer customer) { customerDao.returnCar(customer); }
    public List<Car> findFreeCarByCompany(Company company) {
        return carDao.selectFreeCarsByCompany(company.getId());
    }

    public void rentCar(Customer customer) {
        customerDao.rentCar(customer);
    }

    private String dbUrlFromArgs(String [] args) {
        String dbName;
        dbName = Arrays.asList(args).contains("-databaseFilename") ? args[1] : "carsharing";
        return DB_URL + dbName;
    }
}
