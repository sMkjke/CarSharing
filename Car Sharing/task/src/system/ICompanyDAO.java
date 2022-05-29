package system;

import entity.Company;

import java.util.List;

public interface ICompanyDAO {

    void create(Company company);

    Company get(int id);

    List<Company> getAll();

    void update(Company company);

    void delete(Company company);
}

