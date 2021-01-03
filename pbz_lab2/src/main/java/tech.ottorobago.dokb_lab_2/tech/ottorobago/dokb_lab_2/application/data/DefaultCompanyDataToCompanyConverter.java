package tech.ottorobago.dokb_lab_2.application.data;

import tech.ottorobago.dokb_lab_2.domain.Company;
import tech.ottorobago.dokb_lab_2.domain.factories.CompanyFactory;

public class DefaultCompanyDataToCompanyConverter implements CompanyDataToCompanyConverter {
    private CompanyFactory companyFactory;

    public DefaultCompanyDataToCompanyConverter(CompanyFactory companyFactory) {
        this.companyFactory = companyFactory;
    }

    @Override
    public Company convertCompanyDataToCompany(CompanyData companyData) {
        return companyFactory.createCompany(companyData.getId(), companyData.getName());
    }
}
