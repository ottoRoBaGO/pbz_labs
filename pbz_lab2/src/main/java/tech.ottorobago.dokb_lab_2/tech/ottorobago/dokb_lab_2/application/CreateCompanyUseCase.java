package tech.ottorobago.dokb_lab_2.application;

import tech.ottorobago.dokb_lab_2.application.data.CompanyData;

public interface CreateCompanyUseCase {
    /*returns data with set id*/
    CompanyData createCompany(CompanyData companyData) throws ApplicationException;
}
