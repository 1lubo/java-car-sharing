package carsharing;

import carsharing.storage.dao.DbCarsharingDao;
import carsharing.userMenu.userMenu;


public class Main {

    public static void main(String[] args) {
        // write your code here
        DbCarsharingDao dbCarsharingDao = new DbCarsharingDao(args);
        new userMenu(dbCarsharingDao).start();
    }
}
