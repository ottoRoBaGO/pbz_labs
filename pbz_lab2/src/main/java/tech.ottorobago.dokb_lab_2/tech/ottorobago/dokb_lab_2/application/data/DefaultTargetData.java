package tech.ottorobago.dokb_lab_2.application.data;

public class DefaultTargetData implements TargetData {
    private int id;
    private String name;
    private double distance;

    public DefaultTargetData(int id, String name, double distance) {
        this.id = id;
        this.name = name;
        this.distance = distance;
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
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDistance(double distance) {
        this.distance = distance;
    }
}
