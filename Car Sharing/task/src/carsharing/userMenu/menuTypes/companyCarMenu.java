package carsharing.userMenu.menuTypes;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import carsharing.storage.dao.DbCarsharingDao;
import carsharing.storedObjects.car.Car;
import carsharing.userMenu.userMenu;

public class companyCarMenu extends abstractMenu{
    private final userMenu userMenu;
    private final Scanner scanner;
    private final DbCarsharingDao dbCarsharingDao;
    private int companyId;

    public companyCarMenu(DbCarsharingDao dbCarsharingDao, Scanner scanner, userMenu userMenu) {
        this.userMenu = userMenu;
        this.scanner = scanner;
        this.dbCarsharingDao = dbCarsharingDao;
        this.companyId = 0;
    }

    @Override
    protected List<String> listOfOptions() {
        return List.of("Car list", "Create a car", "Back");
    }

    @Override
    public void executeOption(Integer option) {
        switch (option) {
            case 0:
                userMenu.switchMenu(1);
                break;
            case 1:
                if (dbCarsharingDao.findCarsByCompanyId(companyId).isEmpty()) {
                    System.out.println("The car list is empty!\n");
                } else {
                    listCars();
                }
                break;
            case 2:
                createCar();
                break;
        }
    }

    private void listCars() {
        AtomicInteger idx = new AtomicInteger();
        idx.set(1);
        System.out.println("Car list:");
        dbCarsharingDao.findCarsByCompanyId(companyId).forEach(car -> {
            System.out.printf("%d. %s\n", idx.get(), car.getName());
            idx.getAndIncrement();
        });
        System.out.println("\n");
    }

    private void createCar() {
        System.out.println("Enter the car name:");
        scanner.nextLine();
        String name = scanner.nextLine();
        dbCarsharingDao.add(new Car(name, companyId));
    }

    public void setCompanyId(int companyId) { this.companyId = companyId; }
}
