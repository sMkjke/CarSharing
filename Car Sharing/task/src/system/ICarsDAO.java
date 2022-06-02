package system;

import entity.Car;

import java.util.List;

public interface ICarsDAO {


    void create(Car car, int id);

    Car get(int id);

    List<Car> getAll(int id);

}
