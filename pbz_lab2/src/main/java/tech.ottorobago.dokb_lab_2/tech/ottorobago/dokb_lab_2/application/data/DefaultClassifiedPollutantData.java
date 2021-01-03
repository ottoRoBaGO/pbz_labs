package tech.ottorobago.dokb_lab_2.application.data;

public class DefaultClassifiedPollutantData implements ClassifiedPollutantData {
    private int id, company, pollutant;
    private String dangerClass, lfvGroup;

    public DefaultClassifiedPollutantData(int id, int company, int pollutant, String dangerClass, String lfvGroup) {
        this.id = id;
        this.company = company;
        this.pollutant = pollutant;
        this.dangerClass = dangerClass;
        this.lfvGroup = lfvGroup;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getCompany() {
        return company;
    }

    @Override
    public int getPollutant() {
        return pollutant;
    }

    @Override
    public String getDangerClass() {
        return dangerClass;
    }

    @Override
    public String getLfvGroup() {
        return lfvGroup;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setCompany(int company) {
        this.company = company;
    }

    @Override
    public void setPollutant(int pollutant) {
        this.pollutant = pollutant;
    }

    @Override
    public void setDangerClass(String dangerClass) {
        this.dangerClass = dangerClass;
    }

    @Override
    public void setLfvGroup(String lfvGroup) {
        this.lfvGroup = lfvGroup;
    }
}
