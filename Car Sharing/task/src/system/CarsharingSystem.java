package system;

import java.util.List;

public class CarsharingSystem {

//    private AtomicInteger companyID = new AtomicInteger(0);
    private int companyId;
    private final ICompanyDAO companyDAO;


    public CarsharingSystem(String fileName) {
        this(new CompanyDAOImpl(fileName));
    }

    CarsharingSystem(ICompanyDAO dao) {
        this.companyDAO = dao;
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

