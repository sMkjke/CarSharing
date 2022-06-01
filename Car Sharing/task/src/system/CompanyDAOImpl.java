package system;

import entity.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl extends AConnection implements ICompanyDAO{

//    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:./src/carsharing/db/";
    private static final String CREATE_NEW_TABLE = "DROP TABLE IF EXISTS CAR;\n" +
            "DROP TABLE IF EXISTS COMPANY;\n" +
            "CREATE TABLE IF not EXISTS COMPANY " +
            "(ID INTEGER not NULL AUTO_INCREMENT PRIMARY KEY , " +
            " NAME VARCHAR(255) UNIQUE NOT NULL)";

    private String fileName;


    public CompanyDAOImpl(String fileName) {
        this.fileName = fileName;
        createIfNotExists();
    }

    void createIfNotExists() {
        Statement stmt = null;
        try {
            Connection conn = connect(URL,fileName);
            stmt = conn.createStatement();
            stmt.execute(CREATE_NEW_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Company company) {
        try {
            Connection conn = connect(URL,fileName);
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT into COMPANY (name) values (?)  ");
            statement.setString(1, company.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Company get(int id) {
        try (Connection conn = connect(URL,fileName);
             PreparedStatement statement = conn.prepareStatement("select * from COMPANY " +
                     "where ID = ? ")) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Company(rs.getInt(1),
                        rs.getString(2));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Company> getAll() {
        ArrayList<Company> list = new ArrayList<>();
        try (Connection conn = connect(URL,fileName);
             PreparedStatement statement = conn.prepareStatement("select * from COMPANY")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Company company = new Company(rs.getInt("ID"), rs.getString("name"));
                list.add(company);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void update(Company company) {
// will be realised
    }

    @Override
    public void delete(Company company) {
// will be realised
    }
}
