package system;

import entity.Car;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CarsDAOImpl extends AConnection implements ICarsDAO {


    private static final String CREATE_NEW_TABLE = "CREATE TABLE IF not EXISTS CARS " +
            "(ID INTEGER not NULL AUTO_INCREMENT PRIMARY KEY, " +
            " NAME VARCHAR(255) UNIQUE NOT NULL UNIQUE, " +
            " FK_COMPANY_ID INTEGER not NULL, " +
            " FOREIGN KEY (FK_COMPANY_ID) REFERENCES COMPANY(PK_COMPANY_ID))";

    private String fileName;

    public CarsDAOImpl(String fileName) {
        this.fileName = fileName;
        createIfNoExist();
    }

    void createIfNoExist() {
        Statement stmt = null;
        try {
            java.sql.Connection conn = this.connect(URL, fileName);
            stmt = conn.createStatement();
            stmt.execute(CREATE_NEW_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
