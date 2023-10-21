package carsharing.storage.dao;

import carsharing.storage.DbClient;
import carsharing.storedObjects.car.Car;
import carsharing.storedObjects.car.CarDao;

import java.util.List;

public class DbCarDao implements CarDao {
    private static final String SELECT_ALL = "SELECT * FROM CAR";
    private static final String SELECT = "SELECT * FROM CAR WHERE id = %d";
    private static final String SELECT_BY_COMPANY = "SELECT * FROM CAR WHERE COMPANY_ID = %d";
    private static final String SELECT_FREE_BY_COMPANY = "SELECT * FROM CAR LEFT JOIN CUSTOMER " +
            "ON CUSTOMER.RENTED_CAR_ID = CAR.ID WHERE CUSTOMER.RENTED_CAR_ID IS NULL AND CAR.COMPANY_ID = %d";

    private static final String INSERT_DATA = "INSERT INTO CAR(NAME, COMPANY_ID) VALUES ('%s', %d)";
    private final DbClient dbClient;
    public DbCarDao(DbClient dbClient) {

        this.dbClient = dbClient;
    }

    @Override
    public Car findById(int id) {
        Car car = dbClient.selectCar(String.format(SELECT,id));

        if (car == null) {
            System.out.println("No car found with id " + id);
            return null;
        } else {
            System.out.println("Car found with id " + id);
            return car;
        }
    }

    @Override
    public void add(Car car) {
        dbClient.run(String.format(INSERT_DATA, car.getName(), car.getCompanyId()));
        System.out.println("The car was created!\n");
    }

    @Override
    public boolean isEmpty() {
        return dbClient.selectCarsForList(SELECT_ALL).isEmpty();
    }

    public List<Car> selectCarsByCompany(int company_id) {
        return dbClient.selectCarsForList(String.format(SELECT_BY_COMPANY, company_id));
    }

    public List<Car> selectFreeCarsByCompany(int company_id) {
        return dbClient.selectCarsForList(String.format(SELECT_FREE_BY_COMPANY, company_id));
    }
}
