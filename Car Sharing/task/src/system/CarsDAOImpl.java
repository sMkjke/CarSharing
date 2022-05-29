package system;

import entity.Car;

import java.util.List;

public class CarsDAOImpl implements ICarsDAO {
    public CarsDAOImpl() {
        creatIfNoExist();
    }

     void creatIfNoExist(){

    }

    @Override
    public void create(Car car) {

    }

    @Override
    public Car get(int id) {
        return null;
    }

    @Override
    public List<Car> getAll() {
        return null;
    }
}
