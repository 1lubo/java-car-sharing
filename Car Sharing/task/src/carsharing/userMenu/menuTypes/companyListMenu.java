package carsharing.userMenu.menuTypes;

import carsharing.storage.dao.DbCarsharingDao;
import carsharing.storedObjects.company.Company;
import carsharing.userMenu.userMenu;

import java.util.ArrayList;
import java.util.List;

public class companyListMenu extends abstractMenu {
    private final DbCarsharingDao dbCarsharingDao;
    private final userMenu userMenu;

    public companyListMenu(DbCarsharingDao dbCarsharingDao, userMenu userMenu) {
        this.dbCarsharingDao = dbCarsharingDao;
        this.userMenu = userMenu;
    }
    @Override
    protected List<String> listOfOptions() {
        List<String> options = new ArrayList<>();
        dbCarsharingDao.findAllCompanies().forEach(company -> options.add(company.getName()));
        options.add("Back");
        return options;

    }

    @Override
    public void executeOption(Integer option) {
        if (option == 0) {
            userMenu.switchMenu(1);
        } else if (option <= dbCarsharingDao.findAllCompanies().size() && option >= 0) {
            Company company = dbCarsharingDao.findAllCompanies().get(option - 1);
            System.out.println("'" + company.getName() + "'" + " company");
            userMenu.setCompanyId(company.getId());
            userMenu.switchMenu(3);
        }

    }
}
