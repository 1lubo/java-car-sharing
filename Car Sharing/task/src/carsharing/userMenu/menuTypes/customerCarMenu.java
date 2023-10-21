package carsharing.userMenu.menuTypes;

import carsharing.storage.dao.DbCarsharingDao;
import carsharing.storedObjects.company.Company;
import carsharing.storedObjects.customer.Customer;
import carsharing.storedObjects.car.Car;
import carsharing.userMenu.userMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class customerCarMenu extends abstractMenu {
    private final userMenu userMenu;
    private final Scanner scanner;
    private final DbCarsharingDao dbCarsharingDao;

    private Customer customer;
    private Company company;
    private Car car;
    private List<Car> freeCars;

    public customerCarMenu(DbCarsharingDao dbCarsharingDao, Scanner scanner, userMenu userMenu) {
        this.userMenu = userMenu;
        this.scanner = scanner;
        this.dbCarsharingDao = dbCarsharingDao;
        this.customer = null;
        this.company = null;
        this.car = null;
        this.freeCars = new ArrayList<>();


    }
    @Override
    protected List<String> listOfOptions() {
        return List.of("Rent a car", "Return a rented car", "My rented car", "Back");
    }

    @Override
    public void executeOption(Integer option) {
        switch (option) {
            case 0:
                userMenu.switchMenu(0);
                break;
            case 1:
                if (!customer.noCarRented()) {
                    System.out.println("You've already rented a car!");
                } else {
                    if (dbCarsharingDao.findAllCompanies().isEmpty()) {
                        System.out.println("The company list is empty!");
                    } else {
                        rentCar();
                    }
                }
                break;
            case 2:
                if (customer.noCarRented()) {
                    System.out.println("You didn't rent a car!");
                } else {
                    returnCar();
                }
                break;
            case 3:
                if (customer.noCarRented()) {
                    System.out.println("You didn't rent a car!");
                } else {
                    showRentCar();
                }
                break;
            default:
                System.out.println("Invalid option!\n");
                break;
        }

    }

    private void returnCar() {
        customer.returnCar();
        dbCarsharingDao.returnRentedCar(customer);
        System.out.println("You've returned a rented car!");
    }

    private void showRentCar() {
        Car rentedCar = dbCarsharingDao.findCarById(customer.getRentedCarId());
        Company rentedCarCompany = dbCarsharingDao.findCompanyById(rentedCar.getCompanyId());
        System.out.println("Your rented car:");
        System.out.println(rentedCar.getName());
        System.out.println("Company");
        System.out.println(rentedCarCompany.getName());

    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (customer.getRentedCarId() == null) {
            customer.setRentedCarId(0);
        }
    }

    public void rentCar() {
        List<Company> allCompanies = dbCarsharingDao.findAllCompanies();
        listCompanies(allCompanies);
        int companyOption = scanner.nextInt();
        if (companyOption == 0) {
            userMenu.switchMenu(5);
            return;
        } else if (companyOption <= dbCarsharingDao.findAllCompanies().size() && companyOption > 0) {
            company = allCompanies.get(companyOption - 1);
            freeCars = dbCarsharingDao.findFreeCarByCompany(company);
        }
        if (freeCars.isEmpty()){
           System.out.printf("No available cars in the %s company\n", company.getName());
        } else {
            listCars();
            int carOption = scanner.nextInt();
            if (carOption == 0) {
                userMenu.switchMenu(5);
            } else if ( carOption <= freeCars.size() && carOption > 0) {
                car = freeCars.get(carOption - 1);
                customer.rentCar(car.getId());
                dbCarsharingDao.rentCar(customer);
                System.out.printf("You rented '%s'\n", car.getName());
                userMenu.switchMenu(5);
            }
        }

    }

    private void listCompanies(List<Company> companyList) {
        AtomicInteger idx = new AtomicInteger();
        idx.set(1);
        System.out.println("Choose a company:");
        companyList.forEach(company -> {
            System.out.printf("%d. %s\n", idx.get(), company.getName());
            idx.getAndIncrement();
        });
        System.out.println("0. Back");
    }

    private void listCars() {
        AtomicInteger idx = new AtomicInteger();
        idx.set(1);
        System.out.println("Choose a car:");
        freeCars.forEach(car -> {
            System.out.printf("%d. %s\n", idx.get(), car.getName());
            idx.getAndIncrement();
        });
        System.out.println("0. Back");
    }
}
