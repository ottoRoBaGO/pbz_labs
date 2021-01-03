package tech.ottorobago.dokb_lab_2.ui.stdcommands.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DefaultCreateCommandView implements CreateCommandView {
    private BufferedReader bufferedReader;

    @Override
    public void init() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String askForCompanyName() throws IOException {
        System.out.print("Name: ");

        return ask();
    }

    @Override
    public void showCompanyCreated() {
        System.out.println("A new company was created");
    }

    @Override
    public String askForDischargeCompany() throws IOException {
        System.out.print("Company: ");

        return ask();
    }

    @Override
    public String askForDischargeName() throws IOException {
        System.out.print("Name: ");

        return ask();
    }

    @Override
    public String askForDischargeUsedTarget() throws IOException {
        System.out.print("Used target id: ");

        return ask();
    }

    @Override
    public String askForDischargeDate() throws IOException {
        System.out.print("Date: ");

        return ask();
    }

    @Override
    public String askForDischargeDiameter() throws IOException {
        System.out.print("Diameter: ");

        return ask();
    }

    @Override
    public String askForDischargeMinimalWaterSpeed() throws IOException {
        System.out.print("Minimal water speed: ");

        return ask();
    }

    @Override
    public String askForDischargeWastewaterConsumption() throws IOException {
        System.out.print("Wastewater consumption: ");

        return ask();
    }

    @Override
    public String askForDischargeAngle() throws IOException {
        System.out.print("Angle: ");

        return ask();
    }

    @Override
    public String askForDischargeDistanceToSurface() throws IOException {
        System.out.print("Distance to surface: ");

        return ask();
    }

    @Override
    public String askForDischargeDistanceToShore() throws IOException {
        System.out.print("Distance to shore: ");

        return ask();
    }

    @Override
    public void showDischargeCreated() {
        System.out.println("A new discharge was created");
    }

    @Override
    public String askForClassifiedPollutantCompany() throws IOException {
        System.out.print("Company id: ");

        return ask();
    }

    @Override
    public String askForClassifiedPollutantPollutant() throws IOException {
        System.out.print("Pollutant id: ");

        return ask();
    }

    @Override
    public String askForClassifiedPollutantDangerClass() throws IOException {
        System.out.print("Danger class: ");

        return ask();
    }

    @Override
    public String askForClassifiedPollutantLfvGroup() throws IOException {
        System.out.print("LFV group: ");

        return ask();
    }

    @Override
    public void showClassifiedPollutantCreated() {
        System.out.println("A new classified pollutant was created");
    }

    @Override
    public String askForDischargedPollutantDischarge() throws IOException {
        System.out.print("Discharge id: ");

        return ask();
    }

    @Override
    public String askForDischargedPollutantPollutant() throws IOException {
        System.out.print("Pollutant id: ");

        return ask();
    }

    @Override
    public String askForDischargedPollutantConcentration() throws IOException {
        System.out.print("Concentration: ");

        return ask();
    }

    @Override
    public String askForDischargedPollutantNcc() throws IOException {
        System.out.print("NCC: ");

        return ask();
    }

    @Override
    public String askForDischargedPollutantBackgroundConcentration() throws IOException {
        System.out.print("Background concentration: ");

        return ask();
    }

    @Override
    public String askForDischargedPollutantMpc() throws IOException {
        System.out.print("MPC: ");

        return ask();
    }

    @Override
    public void showDischargedPollutantCreated() {
        System.out.println("A new discharged pollutant was created");
    }

    private String ask() throws IOException {
        return bufferedReader.readLine();
    }
}
