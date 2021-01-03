package tech.ottorobago.dokb_lab_2.domain;

public class DefaultDischargedPollutant implements DischargedPollutant {
    private int id, discharge, pollutant;
    private double concentration, ncc, backgroundConcentration, mpc;

    public DefaultDischargedPollutant(int id, int discharge) {
        setId(id);
        setDischarge(discharge);
    }

    public DefaultDischargedPollutant(int id, int discharge, int pollutant, double concentration, double ncc,
                                      double backgroundConcentration, double mpc) {
        this(id, discharge);

        setPollutant(pollutant);
        setConcentration(concentration);
        setNcc(ncc);
        setBackgroundConcentration(backgroundConcentration);
        setMpc(mpc);
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
        if (id < 0) throw new IllegalArgumentException("A discharged pollutant's id cannot be negative");

        this.id = id;
    }

    @Override
    public void setDischarge(int discharge) {
        if (discharge < 0) throw new IllegalArgumentException("A discharge's id cannot be negative");

        this.discharge = discharge;
    }

    @Override
    public void setPollutant(int pollutant) {
        if (pollutant < 0) throw new IllegalArgumentException("A pollutant's id cannot be negative");

        this.pollutant = pollutant;
    }

    @Override
    public void setConcentration(double concentration) {
        if (concentration < 0) throw new IllegalArgumentException("The concentration cannot be negative");

        this.concentration = concentration;
    }

    @Override
    public void setNcc(double ncc) {
        if (ncc < 0) throw new IllegalArgumentException("The NCC cannot be negative");

        this.ncc = ncc;
    }

    @Override
    public void setBackgroundConcentration(double backgroundConcentration) {
        if (backgroundConcentration < 0) throw new IllegalArgumentException("The background concentration cannot be" +
                " negative");

        this.backgroundConcentration = backgroundConcentration;
    }

    @Override
    public void setMpc(double mpc) {
        if (mpc < 0) throw new IllegalArgumentException("The MPC cannot be negative");

        this.mpc = mpc;
    }
}
