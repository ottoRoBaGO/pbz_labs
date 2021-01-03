package tech.ottorobago.dokb_lab_2.application.data;

public class DefaultCompanyData implements CompanyData {
    private int id;
    private String name;

    public DefaultCompanyData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
