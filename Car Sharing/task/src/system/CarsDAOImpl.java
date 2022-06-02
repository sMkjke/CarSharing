package system;

import entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsDAOImpl extends AConnection implements ICarsDAO {

    private static final String CREATE_NEW_TABLE = " " + "CREATE TABLE CAR (\n" +
            "   ID INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "   NAME VARCHAR(255) UNIQUE NOT NULL,\n" +
            "   COMPANY_ID INT NOT NULL,\n" +
            "   CONSTRAINT FK_COMPANY FOREIGN KEY (COMPANY_ID)\n" +
            "   REFERENCES COMPANY(ID)\n" +
            "   ON DELETE CASCADE\n" +
            ");";
    private String fileName;

    public CarsDAOImpl(String fileName) {
        this.fileName = fileName;
        createIfNoExist();

    }

    void createIfNoExist() {
        try (Connection conn = connect(URL, fileName)) {
            Statement stmt = conn.createStatement();
            stmt.execute(CREATE_NEW_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Car car, int companyId) {
        try (Connection conn = connect(URL, fileName);
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT into CAR (name, COMPANY_ID) values (?,?)")) {
            statement.setString(1, car.getName());
            statement.setInt(2, companyId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Car get(int id) {
        return null;
    }

    public List<Car> getAll(int companyId) {
        try (Connection conn = connect(URL, fileName);
             PreparedStatement statement = conn.prepareStatement("SELECT ID, NAME FROM CAR WHERE COMPANY_ID = (?);")) {
            statement.setInt(1, companyId);
            ResultSet carsResultSet = statement.executeQuery();
            List<Car> cars = new ArrayList<>();

            while (carsResultSet.next()) {
                Integer id = carsResultSet.getInt("ID");
                String name = carsResultSet.getString("NAME");
                Car car = new Car(id, name);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
