package tech.ottorobago.dokb_lab_2.ui.stdcommands.views;

public class DefaultRemoveCommandView implements RemoveCommandView {
    @Override
    public void init() {

    }

    @Override
    public void showCompanyRemoved() {
        System.out.println("The company was removed");
    }

    @Override
    public void showDischargeRemoved() {
        System.out.println("The discharge was removed");
    }

    @Override
    public void showClassifiedPollutantRemoved() {
        System.out.println("The classified pollutant was removed");
    }

    @Override
    public void showDischargedPollutantRemoved() {
        System.out.println("The discharged pollutant was removed");
    }
}
