package system;

import entitny.Car;
import entitny.Company;

import java.util.List;

public interface ICarsDAO {


    void create(Car car);

    Car get(int id);

    List<Car> getAll();
}
