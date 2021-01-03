package tech.ottorobago.dokb_lab_2.persistence;

import tech.ottorobago.dokb_lab_2.domain.Company;

public interface CompanyPlacer {
    /*returns Company with set id and other fields identical to the passed company*/
    Company createCompany(Company company) throws PersistenceException;

    void saveCompany(Company company) throws PersistenceException;

    void removeCompany(int id) throws PersistenceException;
}
