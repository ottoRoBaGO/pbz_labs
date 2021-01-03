package tech.ottorobago.dokb_lab_2.ui.stdcommands.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DefaultEditCommandView implements EditCommandView {
    private CreateCommandView createCommandView;
    private BufferedReader bufferedReader;

    public DefaultEditCommandView(CreateCommandView createCommandView) {
        this.createCommandView = createCommandView;
    }

    @Override
    public void init() {
        createCommandView.init();

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String askForFieldName() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public void showMessage(String message) {
        showInlineMessage(message + "\n");
    }

    @Override
    public void showInlineMessage(String inlineMessage) {
        System.out.print(inlineMessage);
    }

    @Override
    public String askForCompanyName() throws IOException {
        return createCommandView.askForCompanyName();
    }

    @Override
    public void showCompanyChanged() {
        System.out.println("The company was changed");
    }

    @Override
    public String askForDischargeCompany() throws IOException {
        return createCommandView.askForDischargeCompany();
    }

    @Override
    public String askForDischargeName() throws IOException {
        return createCommandView.askForDischargeName();
    }

    @Override
    public String askForDischargeUsedTarget() throws IOException {
        return createCommandView.askForDischargeUsedTarget();
    }

    @Override
    public String askForDischargeDate() throws IOException {
        return createCommandView.askForDischargeDate();
    }

    @Override
    public String askForDischargeDiameter() throws IOException {
        return createCommandView.askForDischargeDiameter();
    }

    @Override
    public String askForDischargeMinimalWaterSpeed() throws IOException {
        return createCommandView.askForDischargeMinimalWaterSpeed();
    }

    @Override
    public String askForDischargeWastewaterConsumption() throws IOException {
        return createCommandView.askForDischargeWastewaterConsumption();
    }

    @Override
    public String askForDischargeAngle() throws IOException {
        return createCommandView.askForDischargeAngle();
    }

    @Override
    public String askForDischargeDistanceToSurface() throws IOException {
        return createCommandView.askForDischargeDistanceToSurface();
    }

    @Override
    public String askForDischargeDistanceToShore() throws IOException {
        return createCommandView.askForDischargeDistanceToShore();
    }

    @Override
    public void showDischargeChanged() {
        System.out.println("The discharge was changed");
    }

    @Override
    public String askForClassifiedPollutantCompany() throws IOException {
        return createCommandView.askForClassifiedPollutantCompany();
    }

    @Override
    public String askForClassifiedPollutantPollutant() throws IOException {
        return createCommandView.askForClassifiedPollutantPollutant();
    }

    @Override
    public String askForClassifiedPollutantDangerClass() throws IOException {
        return createCommandView.askForClassifiedPollutantDangerClass();
    }

    @Override
    public String askForClassifiedPollutantLfvGroup() throws IOException {
        return createCommandView.askForClassifiedPollutantLfvGroup();
    }

    @Override
    public void showClassifiedPollutantChanged() {
        System.out.println("The classified pollutant was changed");
    }

    @Override
    public String askForDischargedPollutantDischarge() throws IOException {
        return createCommandView.askForDischargedPollutantDischarge();
    }

    @Override
    public String askForDischargedPollutantPollutant() throws IOException {
        return createCommandView.askForDischargedPollutantPollutant();
    }

    @Override
    public String askForDischargedPollutantConcentration() throws IOException {
        return createCommandView.askForDischargedPollutantConcentration();
    }

    @Override
    public String askForDischargedPollutantNcc() throws IOException {
        return createCommandView.askForDischargedPollutantNcc();
    }

    @Override
    public String askForDischargedPollutantBackgroundConcentration() throws IOException {
        return createCommandView.askForDischargedPollutantBackgroundConcentration();
    }

    @Override
    public String askForDischargedPollutantMpc() throws IOException {
        return createCommandView.askForDischargedPollutantMpc();
    }

    @Override
    public void showDischargedPollutantChanged() {
        System.out.println("The discharged pollutant was changed");
    }
}
