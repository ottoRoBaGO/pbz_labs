package tech.ottorobago.dokb_lab_2.domain;

public class DefaultTarget implements Target {
    private int id;
    private String name;
    private double distance;

    public DefaultTarget(int id) {
        setId(id);
    }

    public DefaultTarget(int id, String name, double distance) {
        this(id);

        setName(name);
        setDistance(distance);
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
    public double getDistance() {
        return distance;
    }

    @Override
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("A target's id cannot be negative");

        this.id = id;
    }

    @Override
    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("The name cannot be null");

        this.name = name;
    }

    @Override
    public void setDistance(double distance) {
        if (distance < 0) throw new IllegalArgumentException("The distance cannot be negative");

        this.distance = distance;
    }
}
