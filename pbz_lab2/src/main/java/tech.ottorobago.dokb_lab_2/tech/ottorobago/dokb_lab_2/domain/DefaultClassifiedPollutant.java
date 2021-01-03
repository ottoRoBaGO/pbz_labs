package tech.ottorobago.dokb_lab_2.domain;

public class DefaultClassifiedPollutant implements ClassifiedPollutant {
    private int id, company, pollutant;
    private String dangerClass, lfvGroup;

    public DefaultClassifiedPollutant(int id, int company, int pollutant) {
        setId(id);
        setCompany(company);
        setPollutant(pollutant);
    }

    public DefaultClassifiedPollutant(int id, int company, int pollutant, String dangerClass, String lfvGroup) {
        this(id, company, pollutant);

        setDangerClass(dangerClass);
        setLfvGroup(lfvGroup);
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
        if (id < 0) throw new IllegalArgumentException("A classified pollutant's id cannot be negative");

        this.id = id;
    }

    @Override
    public void setCompany(int company) {
        if (company < 0) throw new IllegalArgumentException("A company's id cannot be negative");

        this.company = company;
    }

    @Override
    public void setPollutant(int pollutant) {
        if (pollutant < 0) throw new IllegalArgumentException("A pollutant's id cannot be negative");

        this.pollutant = pollutant;
    }

    @Override

    public void setDangerClass(String dangerClass) {
        if (dangerClass == null) throw new IllegalArgumentException("The danger class cannot be null");

        this.dangerClass = dangerClass;
    }

    @Override
    public void setLfvGroup(String lfvGroup) {
        if (lfvGroup == null) throw new IllegalArgumentException("The LFV group cannot be null");

        this.lfvGroup = lfvGroup;
    }
}
