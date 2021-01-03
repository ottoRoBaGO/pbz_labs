package tech.ottorobago.dokb_lab_2.domain;

public class DefaultPollutant implements Pollutant {
    private int id;
    private String name;

    public DefaultPollutant(int id) {
        setId(id);
    }

    public DefaultPollutant(int id, String name) {
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
        if (id < 0) throw new IllegalArgumentException("A pollutant's id cannot be negative");

        this.id = id;
    }

    @Override
    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("The name cannot be null");

        this.name = name;
    }
}
