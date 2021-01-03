package tech.ottorobago.dokb_lab_2.domain;

public interface ClassifiedPollutant {
    int getId();

    int getCompany();

    int getPollutant();

    String getDangerClass();

    String getLfvGroup();

    /**this method is not supposed to be used with purpose of change the id field
     * in a persistence storage, it's supposed to change the id field only in context of java*/
    void setId(int id);

    void setCompany(int company);

    void setPollutant(int pollutant);

    void setDangerClass(String dangerClass);

    void setLfvGroup(String lfvGroup);
}
