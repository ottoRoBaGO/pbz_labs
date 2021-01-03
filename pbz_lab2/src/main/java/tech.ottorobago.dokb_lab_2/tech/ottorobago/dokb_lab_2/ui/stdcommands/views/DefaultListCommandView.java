package tech.ottorobago.dokb_lab_2.ui.stdcommands.views;

import tech.ottorobago.dokb_lab_2.application.data.*;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class DefaultListCommandView implements ListCommandView {
    private DateFormat dateFormat;
    private NumberFormat numberFormat;

    @Override
    public void init() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        numberFormat = new DecimalFormat("#0.00");
    }

    @Override
    public void showTarget(TargetData targetData) {
        System.out.println("ID: " + targetData.getId() + ", NAME: " + targetData.getName()
                + ", DSTC: " + targetData.getDistance());
    }

    @Override
    public void showPollutant(PollutantData pollutantData) {
        System.out.println("ID: " + pollutantData.getId() + ", NAME: " + pollutantData.getName());
    }

    @Override
    public void showCompany(CompanyData companyData) {
        System.out.println("ID: " + companyData.getId() + ", NAME: " + companyData.getName());
    }

    @Override
    public void showDischarge(DischargeData dischargeData) {
        System.out.println("ID: " + dischargeData.getId() + ", NAME: " + dischargeData.getName() +
                ", CMPN: " + dischargeData.getCompany() +
                ", TRGT: " + dischargeData.getUsedTarget() +
                ", DATE: " + dateFormat.format(dischargeData.getDate()) +
                ", DIAM: " + numberFormat.format(dischargeData.getDiameter()) +
                ", MWSP: " + numberFormat.format(dischargeData.getMinimalWaterSpeed()) +
                ", WWCN: " + numberFormat.format(dischargeData.getWastewaterConsumption()) +
                ", ANGL: " + numberFormat.format(dischargeData.getAngle()) + " deg" +
                ", DSSF: " + numberFormat.format(dischargeData.getDistanceToSurface()) +
                ", DSSR: " + numberFormat.format(dischargeData.getDistanceToShore()));
    }

    @Override
    public void showClassifiedPollutant(ClassifiedPollutantData classifiedPollutantData) {
        System.out.println("ID: " + classifiedPollutantData.getId() +
                ", POLL: " + classifiedPollutantData.getPollutant() +
                ", CMPN: " + classifiedPollutantData.getCompany() +
                ", DNCL: " + classifiedPollutantData.getDangerClass() +
                ", LFVG: " + classifiedPollutantData.getLfvGroup());
    }

    @Override
    public void showDischargedPollutant(DischargedPollutantData dischargedPollutantData) {
        System.out.println("ID: " + dischargedPollutantData.getId() +
                ", DSRG: " + dischargedPollutantData.getDischarge() +
                ", POLL: " + dischargedPollutantData.getPollutant() +
                ", BCTN: " + dischargedPollutantData.getBackgroundConcentration() +
                ", CTN: " + dischargedPollutantData.getConcentration() +
                ", MPC: " + dischargedPollutantData.getMpc() +
                ", NCC: " + dischargedPollutantData.getNcc());
    }
}
