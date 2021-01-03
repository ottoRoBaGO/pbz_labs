package tech.ottorobago.dokb_lab_2.application.data;

public interface ClassifiedPollutantData {
    int getId();

    int getCompany();

    int getPollutant();

    String getDangerClass();

    String getLfvGroup();

    void setId(int id);

    void setCompany(int company);

    void setPollutant(int pollutant);

    void setDangerClass(String dangerClass);

    void setLfvGroup(String lfvGroup);
}
