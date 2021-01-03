package tech.ottorobago.dokb_lab_2.domain.factories;

import tech.ottorobago.dokb_lab_2.domain.Company;

public interface CompanyFactory {
    Company createCompany(int id, String name);
}
