package tech.ottorobago.dokb_lab_2.ui.stdcommands.views;

import tech.ottorobago.dokb_lab_2.application.data.*;
import tech.ottorobago.dokb_lab_2.ui.View;

/*actually it violates SRP and ISP but this is just a lab and i'm so lazy.
 * So in a good way this must be split.*/
public interface ListCommandView extends View {
    void showTarget(TargetData targetData);

    void showPollutant(PollutantData pollutantData);

    void showCompany(CompanyData companyData);

    void showDischarge(DischargeData dischargeData);

    void showClassifiedPollutant(ClassifiedPollutantData classifiedPollutantData);

    void showDischargedPollutant(DischargedPollutantData dischargedPollutantData);
}
