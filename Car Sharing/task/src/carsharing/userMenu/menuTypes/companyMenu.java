package carsharing.userMenu.menuTypes;

import carsharing.storage.dao.DbCarsharingDao;
import carsharing.storedObjects.company.Company;
import carsharing.userMenu.userMenu;

import java.util.List;
import java.util.Scanner;

public class companyMenu extends abstractMenu{

    private final DbCarsharingDao dbCarsharingDaoDao;
    private final Scanner scanner;

    private final userMenu userMenu;

    public companyMenu(DbCarsharingDao dbCarsharingDaoDao, Scanner scanner, userMenu userMenu) {
        this.dbCarsharingDaoDao = dbCarsharingDaoDao;
        this.scanner = scanner;
        this.userMenu = userMenu;
    }

    @Override
    protected List<String> listOfOptions() {
        return List.of("Company list", "Create a company", "Back");
    }

    @Override
    public void executeOption(Integer option) {
        switch (option) {
            case 0:
                userMenu.switchMenu(0);
                break;
            case 1:
                if (dbCarsharingDaoDao.noCompanies()) {
                    System.out.println("The company list is empty!\n");
                } else {
                    System.out.println("Choose the company:");
                    userMenu.switchMenu(2);
                }
                break;
            case 2:
                createCompany();
                break;
            default:
                System.out.println("Invalid option!\n");
                break;
        }
    }

    private void createCompany() {
        System.out.println("Enter the company name:");
        scanner.nextLine();
        String name = scanner.nextLine();
        dbCarsharingDaoDao.add(new Company(name));
    }
}
