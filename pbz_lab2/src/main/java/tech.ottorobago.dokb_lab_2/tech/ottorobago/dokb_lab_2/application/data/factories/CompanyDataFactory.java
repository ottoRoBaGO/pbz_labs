package tech.ottorobago.dokb_lab_2.application.data.factories;

import tech.ottorobago.dokb_lab_2.application.data.CompanyData;

public interface CompanyDataFactory {
    CompanyData createCompanyData(int id, String name);
}
