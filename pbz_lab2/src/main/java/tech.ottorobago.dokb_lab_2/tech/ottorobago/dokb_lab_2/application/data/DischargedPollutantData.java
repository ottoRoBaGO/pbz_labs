package tech.ottorobago.dokb_lab_2.application.data;

public interface DischargedPollutantData {
    int getId();

    int getDischarge();

    int getPollutant();

    double getConcentration();

    double getNcc();

    double getBackgroundConcentration();

    double getMpc();

    void setId(int id);

    void setDischarge(int discharge);

    void setPollutant(int pollutant);

    void setConcentration(double concentration);

    void setNcc(double ncc);

    void setBackgroundConcentration(double backgroundConcentration);

    void setMpc(double mpc);
}
