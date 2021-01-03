package tech.ottorobago.dokb_lab_2.application.data;

public class DefaultDischargedPollutantData implements DischargedPollutantData {
    private int id, discharge, pollutant;
    private double concentration, ncc, backgroundConcentration, mpc;

    public DefaultDischargedPollutantData(int id, int discharge, int pollutant, double concentration,
                                          double ncc, double backgroundConcentration, double mpc) {
        this.id = id;
        this.discharge = discharge;
        this.pollutant = pollutant;
        this.concentration = concentration;
        this.ncc = ncc;
        this.backgroundConcentration = backgroundConcentration;
        this.mpc = mpc;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getDischarge() {
        return discharge;
    }

    @Override
    public int getPollutant() {
        return pollutant;
    }

    @Override
    public double getConcentration() {
        return concentration;
    }

    @Override
    public double getNcc() {
        return ncc;
    }

    @Override
    public double getBackgroundConcentration() {
        return backgroundConcentration;
    }

    @Override
    public double getMpc() {
        return mpc;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setDischarge(int discharge) {
        this.discharge = discharge;
    }

    @Override
    public void setPollutant(int pollutant) {
        this.pollutant = pollutant;
    }

    @Override
    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }

    @Override
    public void setNcc(double ncc) {
        this.ncc = ncc;
    }

    @Override
    public void setBackgroundConcentration(double backgroundConcentration) {
        this.backgroundConcentration = backgroundConcentration;
    }

    @Override
    public void setMpc(double mpc) {
        this.mpc = mpc;
    }
}
