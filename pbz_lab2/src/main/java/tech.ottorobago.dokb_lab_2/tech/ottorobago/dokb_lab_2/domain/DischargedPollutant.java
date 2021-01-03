package tech.ottorobago.dokb_lab_2.domain;

public interface DischargedPollutant {
    int getId();

    int getDischarge();

    int getPollutant();

    double getConcentration();

    double getNcc();

    double getBackgroundConcentration();

    double getMpc();

    /**this method is not supposed to be used with purpose of change the id field
     * in a persistence storage, it's supposed to change the id field only in context of java*/
    void setId(int id);

    void setDischarge(int discharge);

    void setPollutant(int pollutant);

    void setConcentration(double concentration);

    void setNcc(double ncc);

    void setBackgroundConcentration(double backgroundConcentration);

    void setMpc(double mpc);
}
