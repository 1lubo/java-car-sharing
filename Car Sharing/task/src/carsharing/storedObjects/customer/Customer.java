package carsharing.storedObjects.customer;

public class Customer {
    private String name;
    private int id;
    private Integer rentedCarId;

    public Customer(){}

    public Customer(String name){
        this.name = name;
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

    public Integer getRentedCarId() {
        return rentedCarId;
    }

    public void setRentedCarId(Integer rentedCarId) {
        this.rentedCarId = rentedCarId;
    }

    public void rentCar(int carId) {
        rentedCarId = carId;
    }
    public void returnCar() {
        rentedCarId = 0;
    }

    public boolean noCarRented() {
        return rentedCarId == 0;
    }
    @Override
    public String toString() {
        return id + ". " + name;
    }
}
