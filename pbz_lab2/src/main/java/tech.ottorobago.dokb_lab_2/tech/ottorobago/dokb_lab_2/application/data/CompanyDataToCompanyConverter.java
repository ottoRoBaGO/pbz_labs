package tech.ottorobago.dokb_lab_2.application.data;

import tech.ottorobago.dokb_lab_2.domain.Company;

public interface CompanyDataToCompanyConverter {
    Company convertCompanyDataToCompany(CompanyData companyData);
}
