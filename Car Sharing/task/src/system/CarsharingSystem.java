package system;

import entity.Car;
import entity.Company;

import java.nio.channels.AcceptPendingException;
import java.util.List;

public class CarsharingSystem {

//    private AtomicInteger companyID = new AtomicInteger(0);
    private int companyId;
    private int carId;
    private final ICompanyDAO companyDAO;
    private final ICarsDAO carsDAO;

//    private static final String URL = "jdbc:h2:./src/carsharing/db/";


    public CarsharingSystem(String fileName) {
        this(new CompanyDAOImpl(fileName), new CarsDAOImpl(fileName));
    }

    CarsharingSystem(ICompanyDAO companyDAO, ICarsDAO carsDAO) {
        this.companyDAO = companyDAO;
        this.carsDAO = carsDAO;
    }


    public int getCompanyID() {
        return companyId;
    }

    public int getCarId() {
        return carId;
    }

    public void addCompanyToDB(Company company) {
        companyDAO.create(company);
//        companyID.addAndGet(1);
    }

    public void addCarToDB(Car car, int id) {
        carsDAO.create(car, id);
    }

    public Company getCompany(int ID){
       return companyDAO.get(ID);
    }


    public boolean getAllCompanies() {
        List<Company> list = companyDAO.getAll();
        if (!list.isEmpty()) {
            System.out.println("Choose the company: ");
            for (Company i : list) {
                System.out.println(i.toString());
            }
        } else {
            System.out.println("The company list is empty!");
            return false;
        }
        return true;
    }

    public boolean getAllCars(int id) {
        List<Car> list = carsDAO.getAll(id);
        if (!list.isEmpty()) {
            System.out.println("Car list:");
            for (Car i : list) {
                System.out.println(i.toString());
            }
        } else {
            System.out.println("The car list is empty!");
            return false;
        }
        return true;
    }
}

