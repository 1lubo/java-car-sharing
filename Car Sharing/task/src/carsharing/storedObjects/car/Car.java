package carsharing.storedObjects.car;

public class Car {
    private int id;
    private String name;
    private int companyId;

    public Car() {}

    public Car(String name, int companyId) {
        this.name = name;
        this.id = 0;
        this.companyId = companyId;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String toString() {
        return id + ". " + name;
    }
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

}
