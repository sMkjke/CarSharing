package system;

import system.Company;

import java.util.List;

public interface ICompanyDAO {

    public void create(Company company);

    public Company get(int id);

    public List<Company> getAll();

    public void update(Company company);

    public void delete(Company company);
}

