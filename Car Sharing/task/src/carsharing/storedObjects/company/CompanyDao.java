package carsharing.storedObjects.company;

import java.util.List;

public interface CompanyDao {
    List<Company> findAll();
    Company findById(int id);
    void add(Company company);
    boolean isEmpty();
}
