package tech.ottorobago.dokb_lab_2.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.persistence.CompanyPlacer;
import tech.ottorobago.dokb_lab_2.persistence.PersistenceException;

public class DefaultRemoveCompanyUseCase implements RemoveCompanyUseCase {
    private CompanyPlacer companyPlacer;

    private static final Logger logger = LogManager.getLogger(DefaultRemoveCompanyUseCase.class.getName());

    public DefaultRemoveCompanyUseCase(CompanyPlacer companyPlacer) {
        this.companyPlacer = companyPlacer;
    }

    @Override
    public void removeCompany(int company) throws ApplicationException {
        try {
            companyPlacer.removeCompany(company);
        } catch (PersistenceException exc) {
            String mes = "Unable to remove data from the persistence layer";

            logger.error(mes, exc);

            throw new ApplicationException(mes);
        }
    }
}
