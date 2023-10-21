package carsharing.storage;

import carsharing.storedObjects.car.Car;
import carsharing.storedObjects.company.Company;
import carsharing.storedObjects.customer.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbClient {
    final String JDBC_DRIVER = "org.h2.Driver";
    private final String url;

    public DbClient(String url) {
        this.url = url;
    }

    public void run(String sql) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find driver");
            e.printStackTrace();
        }  try (
             Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()
        ) {
            conn.setAutoCommit(true);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Something is wrong with the sql statement");
            e.printStackTrace();
        }
    }

    public Company selectCompany(String sql) {
        List<Company> companies = selectCompaniesForList(sql);
        if (companies.size() == 1) {
            return companies.get(0);
        } else if (companies.isEmpty()) {
            return null;
        } else
            throw new IllegalStateException("More than one company returned");
    }

    public Car selectCar(String sql) {
        List<Car> cars = selectCarsForList(sql);
        if (cars.size() == 1) {
            return cars.get(0);
        } else if (cars.isEmpty()) {
            return null;
        } else
            throw new IllegalStateException("More than one car returned");
    }

    public List<Company> selectCompaniesForList(String sql) {
        List<Company> companies = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find driver");
            e.printStackTrace();
        } try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Company company = new Company();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                companies.add(company);
            }
            return companies;
        } catch (SQLException e) {
            System.out.println("Unable to execute SQL: " + sql);
            e.printStackTrace();
        }
        return companies;
    }

    public List<Car> selectCarsForList(String sql) {
        List<Car> cars = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find driver");
            e.printStackTrace();
        } try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setName(rs.getString("name"));
                car.setCompanyId(rs.getInt("company_id"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            System.out.println("Unable to execute SQL: " + sql);
            e.printStackTrace();
        }
        return cars;
    }

    public List<Customer> selectCustomersForList(String sql) {
        List<Customer> customers = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find driver");
            e.printStackTrace();
        } try (Connection conn = DriverManager.getConnection(url);
               Statement stmt = conn.createStatement();
               ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            System.out.println("Unable to execute SQL: " + sql);
            e.printStackTrace();
        }
        return customers;
    }
}
