package tech.ottorobago.dokb_lab_2.domain;

import java.util.Date;

public class DefaultDischarge implements Discharge {
    private int id, company, usedTarget;
    private String name;
    private Date date;
    private double diameter, minimalWaterSpeed, wastewaterConsumption,
           angle, distanceToSurface, distanceToShore;

    public DefaultDischarge(int id, int company) {
        setId(id);
        setCompany(company);
    }

    public DefaultDischarge(int id, int company, int usedTarget, String name, Date date, double diameter,
                            double minimalWaterSpeed, double wastewaterConsumption, double angle,
                            double distanceToSurface, double distanceToShore) {
        this(id, company);

        setUsedTarget(usedTarget);
        setName(name);
        setDate(date);
        setDiameter(diameter);
        setMinimalWaterSpeed(minimalWaterSpeed);
        setWastewaterConsumption(wastewaterConsumption);
        setAngle(angle);
        setDistanceToSurface(distanceToSurface);
        setDistanceToShore(distanceToShore);
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
    public int getUsedTarget() {
        return usedTarget;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public double getDiameter() {
        return diameter;
    }

    @Override
    public double getMinimalWaterSpeed() {
        return minimalWaterSpeed;
    }

    @Override
    public double getWastewaterConsumption() {
        return wastewaterConsumption;
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public double getDistanceToSurface() {
        return distanceToSurface;
    }

    @Override
    public double getDistanceToShore() {
        return distanceToShore;
    }

    @Override
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("A discharge's id cannot be negative");

        this.id = id;
    }

    @Override
    public void setCompany(int company) {
        if (company < 0) throw new IllegalArgumentException("A company's id cannot be negative");

        this.company = company;
    }

    @Override
    public void setUsedTarget(int usedTarget) {
        if (usedTarget < 0) throw new IllegalArgumentException("A used target's id cannot be negative");

        this.usedTarget = usedTarget;
    }

    @Override
    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("The name cannot be null");

        this.name = name;
    }

    @Override
    public void setDate(Date date) {
        if (date == null) throw new IllegalArgumentException("The date cannot be null");

        this.date = date;
    }

    @Override
    public void setDiameter(double diameter) {
        if (diameter < 0) throw new IllegalArgumentException("The diameter cannot be negative");

        this.diameter = diameter;
    }

    @Override
    public void setMinimalWaterSpeed(double minimalWaterSpeed) {
        if (minimalWaterSpeed < 0) throw new IllegalArgumentException("The minimal water speed cannot be negative");

        this.minimalWaterSpeed = minimalWaterSpeed;
    }

    @Override
    public void setWastewaterConsumption(double wastewaterConsumption) {
        if (wastewaterConsumption < 0) throw new IllegalArgumentException("The wastewater consumption cannot be negative");

        this.wastewaterConsumption = wastewaterConsumption;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public void setDistanceToSurface(double distanceToSurface) {
        if (distanceToSurface < 0) throw new IllegalArgumentException("The distance to the surface cannot be negative");

        this.distanceToSurface = distanceToSurface;
    }

    @Override
    public void setDistanceToShore(double distanceToShore) {
        if (distanceToShore < 0) throw new IllegalArgumentException("The distance to a shore cannot be negative");

        this.distanceToShore = distanceToShore;
    }
}
