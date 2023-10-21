package carsharing.userMenu;

import java.util.Map;
import java.util.Scanner;

import carsharing.storage.dao.DbCarsharingDao;
import carsharing.storedObjects.customer.Customer;
import carsharing.userMenu.menuTypes.*;


public class userMenu {
    private boolean contd;
    private final Scanner scanner;
    private final companyCarMenu companyCarMenu;
    private final customerCarMenu customerCarMenu;
    private Integer currentMenuNum;
    private final Map<Integer, abstractMenu> menuMap;

    public userMenu(DbCarsharingDao dbCarsharingDao) {
        this.contd = true;
        this.scanner = new Scanner(System.in);
        carsharing.userMenu.menuTypes.mainMenu mainMenu = new mainMenu(this, dbCarsharingDao, scanner);
        carsharing.userMenu.menuTypes.companyListMenu companyListMenu = new companyListMenu(dbCarsharingDao, this);
        this.companyCarMenu = new companyCarMenu(dbCarsharingDao, scanner, this);
        carsharing.userMenu.menuTypes.companyMenu companyMenu = new companyMenu(dbCarsharingDao, scanner, this);
        carsharing.userMenu.menuTypes.customerListMenu customerListMenu = new customerListMenu(dbCarsharingDao, this);
        this.customerCarMenu = new customerCarMenu(dbCarsharingDao, scanner, this);
        this.menuMap = Map.of(0, mainMenu, 1, companyMenu, 2, companyListMenu, 3, companyCarMenu,
                4, customerListMenu, 5, customerCarMenu);
        this.currentMenuNum = 0;
    }

    public void start() {

        abstractMenu currentMenu;
        int option;
        while (contd) {
            currentMenu = menuMap.get(currentMenuNum);
            currentMenu.printMenu();
            option = this.scanner.nextInt();
            currentMenu.executeOption(option);
        }
    }

    public void stop() {
        this.contd = false;
    }
    public void switchMenu(int menuNum) {
        this.currentMenuNum = menuNum;
    }

    public void setCompanyId(int companyId) {
        companyCarMenu.setCompanyId(companyId);
    }
    public void setCustomer(Customer customer) { customerCarMenu.setCustomer(customer);}
}
