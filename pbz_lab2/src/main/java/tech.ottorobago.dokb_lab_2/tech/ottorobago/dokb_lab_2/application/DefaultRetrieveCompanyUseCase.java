package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.CompanyData;
import tech.ottorobago.dokb_lab_2.application.data.factories.CompanyDataFactory;
import tech.ottorobago.dokb_lab_2.domain.Company;
import tech.ottorobago.dokb_lab_2.persistence.CompanyRetriever;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;

public class DefaultRetrieveCompanyUseCase implements RetrieveCompanyUseCase {
    private CompanyRetriever companyRetriever;
    private CompanyDataFactory companyDataFactory;

    private static final Logger logger = LogManager.getLogger(DefaultRetrieveCompanyUseCase.class.getName());

    public DefaultRetrieveCompanyUseCase(CompanyRetriever companyRetriever, CompanyDataFactory companyDataFactory) {
        this.companyRetriever = companyRetriever;
        this.companyDataFactory = companyDataFactory;
    }

    @Override
    public List<CompanyData> retrieveAllCompanies() throws ApplicationException {
        try {
            List<Company> companies = companyRetriever.loadAllCompanies();

            List<CompanyData> result = new ArrayList<>();

            for (Company company : companies)
                result.add(convertCompanyToCompanyData(company));

            return result;
        } catch (PersistenceException exc) {
            String mes = "Unable to take the data from the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }

    @Override
    public CompanyData retrieveCompany(int id) throws ApplicationException {
        try {
            Company company = companyRetriever.loadCompany(id);

            return convertCompanyToCompanyData(company);
        } catch (PersistenceException exc) {
            String mes = "Unable to take the data from the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }

    private CompanyData convertCompanyToCompanyData(Company company) {
        return companyDataFactory.createCompanyData(company.getId(), company.getName());
    }
}
