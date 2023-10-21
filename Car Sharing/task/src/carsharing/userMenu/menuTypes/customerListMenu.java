package carsharing.userMenu.menuTypes;

import carsharing.storage.dao.DbCarsharingDao;
import carsharing.userMenu.userMenu;

import java.util.ArrayList;
import java.util.List;

public class customerListMenu extends abstractMenu{

    private final DbCarsharingDao dbCarsharingDao;
    private final userMenu userMenu;

    public customerListMenu(DbCarsharingDao dbCarsharingDao, userMenu userMenu) {
        this.dbCarsharingDao = dbCarsharingDao;
        this.userMenu = userMenu;
    }
    @Override
    protected List<String> listOfOptions() {
        List<String> options = new ArrayList<>();
        dbCarsharingDao.findAllCustomers().forEach(customer -> options.add(customer.getName()));
        options.add("Back");
        return options;
    }

    @Override
    public void executeOption(Integer option) {
        if (option == 0) {
            userMenu.switchMenu(0);
        } else if (option <= dbCarsharingDao.findAllCustomers().size() && option > 0) {
            userMenu.setCustomer(dbCarsharingDao.findAllCustomers().get(option - 1));
            userMenu.switchMenu(5);
        }
    }
}
