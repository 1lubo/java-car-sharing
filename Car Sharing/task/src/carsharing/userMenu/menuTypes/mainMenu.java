package carsharing.userMenu.menuTypes;

import java.util.List;
import java.util.Scanner;

import carsharing.storedObjects.customer.Customer;
import carsharing.userMenu.userMenu;
import carsharing.storage.dao.DbCarsharingDao;

public class mainMenu extends abstractMenu {
    private final userMenu userMenu;
    private final DbCarsharingDao dbCarSharingDao;
    private final Scanner scanner;

    public mainMenu(userMenu userMenu, DbCarsharingDao dbCarsharingDao, Scanner scanner) {
        this.userMenu = userMenu;
        this.dbCarSharingDao = dbCarsharingDao;
        this.scanner = scanner;
    }
    @Override
    protected List<String> listOfOptions() {
        return List.of("Log in as a manager", "Log in as a customer", "Create a customer", "Exit");
    }

    @Override
    public void executeOption(Integer option) {
        switch (option) {
            case 0:
                userMenu.stop();
                break;
            case 1:
                userMenu.switchMenu(1);
                break;
            case 2:
                if (dbCarSharingDao.noCustomers()) {
                    System.out.println("The customer list is empty!");
                } else {
                    System.out.println("Customer list:");
                    userMenu.switchMenu(4);
                }
                break;
            case 3:
                createCustomer();
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }

    }

    private void createCustomer() {
        System.out.println("Enter the customer name:");
        scanner.nextLine();
        String customerName = scanner.nextLine();
        dbCarSharingDao.add(new Customer(customerName));
        System.out.println("The customer was added!");
    }
}
