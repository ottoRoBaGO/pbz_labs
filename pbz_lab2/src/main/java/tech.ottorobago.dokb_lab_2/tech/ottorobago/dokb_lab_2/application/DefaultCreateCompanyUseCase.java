package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.application.data.CompanyData;
import tech.ottorobago.dokb_lab_2.application.data.CompanyDataToCompanyConverter;
import tech.ottorobago.dokb_lab_2.persistence.CompanyPlacer;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

public class DefaultCreateCompanyUseCase implements CreateCompanyUseCase {
    private CompanyPlacer companyPlacer;
    private CompanyDataToCompanyConverter companyDataToCompanyConverter;

    private static final Logger logger = LogManager.getLogger(DefaultChangeCompanyUseCase.class.getName());

    public DefaultCreateCompanyUseCase(CompanyPlacer companyPlacer, CompanyDataToCompanyConverter companyDataToCompanyConverter) {
        this.companyPlacer = companyPlacer;
        this.companyDataToCompanyConverter = companyDataToCompanyConverter;
    }

    @Override
    public CompanyData createCompany(CompanyData companyData) throws ApplicationException {
        try {
            int newId = companyPlacer.createCompany(
                    companyDataToCompanyConverter
                            .convertCompanyDataToCompany(companyData)).getId();

            companyData.setId(newId);

            return companyData;
        } catch (PersistenceException exc) {
            String mes = "Unable to insert data into the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }
}
