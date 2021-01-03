package tech.ottorobago.dokb_lab_2.application;

import tech.ottorobago.dokb_lab_2.application.data.CompanyData;

public interface ChangeCompanyUseCase {
    void changeCompany(CompanyData companyData) throws ApplicationException;
}
