package tech.ottorobago.dokb_lab_2.persistence;

import tech.ottorobago.dokb_lab_2.domain.Company;

import java.util.List;

public interface CompanyRetriever {
    Company loadCompany(int id) throws PersistenceException;

    List<Company> loadAllCompanies() throws PersistenceException;
}
