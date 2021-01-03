package tech.ottorobago.dokb_lab_2.domain;

public class DefaultCompany implements Company {
    private int id;
    private String name;

    public DefaultCompany(int id) {
        setId(id);
    }

    public DefaultCompany(int id, String name) {
        this(id);

        setName(name);
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
        if (id < 0) throw new IllegalArgumentException("A company's id cannot be negative");

        this.id = id;
    }

    @Override
    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("The name cannot be null");

        this.name = name;
    }
}
