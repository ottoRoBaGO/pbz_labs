package tech.ottorobago.dokb_lab_2.ui.stdcommands.views;

import tech.ottorobago.dokb_lab_2.ui.View;

import java.io.IOException;

/*actually it hardly violates SRP and ISP but this is just a lab and i'm so lazy.
 * So in a good way this must be split.*/
public interface CreateCommandView extends View {
    //company
    String askForCompanyName() throws IOException;

    void showCompanyCreated();

    //discharge
    String askForDischargeCompany() throws IOException;

    String askForDischargeName() throws IOException;

    String askForDischargeUsedTarget() throws IOException;

    String askForDischargeDate() throws IOException;

    String askForDischargeDiameter() throws IOException;

    String askForDischargeMinimalWaterSpeed() throws IOException;

    String askForDischargeWastewaterConsumption() throws IOException;

    String askForDischargeAngle() throws IOException;

    String askForDischargeDistanceToSurface() throws IOException;

    String askForDischargeDistanceToShore() throws IOException;

    void showDischargeCreated();

    //classified pollutant
    String askForClassifiedPollutantCompany() throws IOException;

    String askForClassifiedPollutantPollutant() throws IOException;

    String askForClassifiedPollutantDangerClass() throws IOException;

    String askForClassifiedPollutantLfvGroup() throws IOException;

    void showClassifiedPollutantCreated();

    //discharged pollutant
    String askForDischargedPollutantDischarge() throws IOException;

    String askForDischargedPollutantPollutant() throws IOException;

    String askForDischargedPollutantConcentration() throws IOException;

    String askForDischargedPollutantNcc() throws IOException;

    String askForDischargedPollutantBackgroundConcentration() throws IOException;

    String askForDischargedPollutantMpc() throws IOException;

    void showDischargedPollutantCreated();
}
