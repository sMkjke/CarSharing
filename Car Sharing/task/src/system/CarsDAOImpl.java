package system;

import entity.Car;
import entity.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsDAOImpl extends AConnection implements ICarsDAO {

    private static final String CREATE_NEW_TABLE = "CREATE TABLE IF not EXISTS CARS  " + //IF not EXISTS
            "(ID INTEGER AUTO_INCREMENT not NULL PRIMARY KEY, " +
            " NAME VARCHAR(255) NOT NULL UNIQUE, " + //unique
            "FK_COMPANY_ID INTEGER not NULL," +
            " FOREIGN KEY (FK_COMPANY_ID) REFERENCES COMPANY( COMPANY_ID ))";


    private String fileName;

    public CarsDAOImpl(String fileName) {
        this.fileName = fileName;
        createIfNoExist();
    }

    void createIfNoExist() {
        Statement stmt = null;
        try {
            Connection conn = connect(URL, fileName);
            stmt = conn.createStatement();
            stmt.execute(CREATE_NEW_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Car car) {
        try {
            Connection conn = connect(URL, fileName);
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT into CARS (name, FK_COMPANY_ID) values (?,?)  ");
            statement.setString(1, car.getName());
            statement.setInt(2, 7); //for testing
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Car get(int id) {
        return null;
    }

    public List<Car> getAll() {
        ArrayList<Car> list = new ArrayList<>();
        try (Connection conn = connect(URL, fileName);
             PreparedStatement statement = conn.prepareStatement("select * from CARS")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Car car = new Car(rs.getInt("ID"), rs.getString("name"));
                list.add(car);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
