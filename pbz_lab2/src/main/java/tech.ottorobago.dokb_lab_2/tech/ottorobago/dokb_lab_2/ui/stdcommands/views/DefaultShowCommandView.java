package tech.ottorobago.dokb_lab_2.ui.stdcommands.views;

import tech.ottorobago.dokb_lab_2.application.data.DischargeData;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class DefaultShowCommandView implements ShowCommandView {
    private DateFormat dateFormat;
    private NumberFormat numberFormat;

    @Override
    public void init() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        numberFormat = new DecimalFormat("#0.00");
    }

    @Override
    public void showDischarge(DischargeData dischargeData) {
        //yeah, i know it's a duplicate but it's justified (trust me)
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
}
