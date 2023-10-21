package carsharing.storedObjects.car;


public interface CarDao {
    Car findById(int id);
    void add(Car car);
    boolean isEmpty();
}
