package tech.ottorobago.dokb_lab_2.ui.stdcommands.views;

import tech.ottorobago.dokb_lab_2.ui.View;

import java.io.IOException;

/*actually it hardly violates SRP and ISP but this is just a lab and i'm so lazy.
 * So in a good way this must be split.*/
public interface EditCommandView extends View {
    String askForFieldName() throws IOException;

    void showMessage(String message);

    void showInlineMessage(String inlineMessage);

    //company
    String askForCompanyName() throws IOException;

    void showCompanyChanged();

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

    void showDischargeChanged();

    //classified pollutant
    String askForClassifiedPollutantCompany() throws IOException;

    String askForClassifiedPollutantPollutant() throws IOException;

    String askForClassifiedPollutantDangerClass() throws IOException;

    String askForClassifiedPollutantLfvGroup() throws IOException;

    void showClassifiedPollutantChanged();

    //discharged pollutant
    String askForDischargedPollutantDischarge() throws IOException;

    String askForDischargedPollutantPollutant() throws IOException;

    String askForDischargedPollutantConcentration() throws IOException;

    String askForDischargedPollutantNcc() throws IOException;

    String askForDischargedPollutantBackgroundConcentration() throws IOException;

    String askForDischargedPollutantMpc() throws IOException;

    void showDischargedPollutantChanged();
}
