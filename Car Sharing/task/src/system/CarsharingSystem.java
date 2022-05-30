package system;

import entity.Company;

import java.util.List;

public class CarsharingSystem {

//    private AtomicInteger companyID = new AtomicInteger(0);
    private int companyId;
    private final ICompanyDAO companyDAO;
    private final ICarsDAO carsDAO;

    private static final String URL = "jdbc:h2:./src/carsharing/db/";


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

    public void addCompanyToDB(Company company) {
        companyDAO.create(company);
//        companyID.addAndGet(1);
    }

    public void getAllCompanies() {
        List<Company> list = companyDAO.getAll();
        if (!list.isEmpty()) {
            System.out.println("Company list:");
            for (Company i : list) {
                System.out.println(i.toString());
            }
        } else {
            System.out.println("The company list is empty!");
        }
    }
}

