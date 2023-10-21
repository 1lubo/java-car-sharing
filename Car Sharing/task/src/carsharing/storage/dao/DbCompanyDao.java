package carsharing.storage.dao;

import carsharing.storage.DbClient;
import carsharing.storedObjects.company.Company;
import carsharing.storedObjects.company.CompanyDao;

import java.util.List;

public class DbCompanyDao implements CompanyDao{

    private static final String SELECT_ALL = "SELECT * FROM COMPANY";
    private static final String SELECT = "SELECT * FROM COMPANY WHERE id = %d";
    private static final String INSERT_DATA = "INSERT INTO COMPANY(NAME) VALUES ('%s')";
    private final DbClient dbClient;

    public DbCompanyDao(DbClient dbClient) {

        this.dbClient = dbClient;
    }

    @Override
    public List<Company> findAll() {
        return dbClient.selectCompaniesForList(SELECT_ALL);
    }

    @Override
    public Company findById(int id) {
        Company company = dbClient.selectCompany(String.format(SELECT,id));

        if (company == null) {
            System.out.println("No company found with id " + id);
            return null;
        } else {
            System.out.println("Company found with id " + id);
            return company;
        }
    }

    @Override
    public void add(Company company) {
        dbClient.run(String.format(INSERT_DATA, company.getName()));
        System.out.println("The company was created!\n");
    }

    @Override
    public boolean isEmpty() {
        return dbClient.selectCompaniesForList(SELECT_ALL).isEmpty();
    }

}
