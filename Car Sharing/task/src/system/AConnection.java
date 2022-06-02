package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AConnection {

    public static final String URL = "jdbc:h2:./src/carsharing/db/";

    static Connection connect(String URL, String fileName) {
        try {
            return DriverManager.
                    getConnection(URL + fileName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
