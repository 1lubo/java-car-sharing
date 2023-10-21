package carsharing.storedObjects.company;

public class Company {
    private String name;
    private int id;

    public Company(){
    }

    public Company(String name){

        this.name = name;
        this.id = 0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return id + ". " + name;
    }
}
