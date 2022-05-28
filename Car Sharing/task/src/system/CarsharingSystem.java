package system;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CarsharingSystem {

    private AtomicInteger companyID = new AtomicInteger(0);
    private final ICompanyDAO companyDAO;

    public CarsharingSystem(String fileName) {
        this(new CompanyDAOImpl(fileName));
    }

    CarsharingSystem(ICompanyDAO dao) {
        this.companyDAO = dao;
    }

    public int getCompanyID() {
        return companyID.get();
    }

    public void addCompanyToDB(Company company) {
        companyDAO.create(company);
        companyID.addAndGet(1);
    }

    public void getAllCompanies() {
        List<Company> list = companyDAO.getAll();
        if (!list.isEmpty()) {
            for (Company i : list) {
                System.out.println(i.toString());
            }
        } else {
            System.out.println("The company list is empty!");
        }
    }
}

