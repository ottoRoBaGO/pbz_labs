package tech.ottorobago.dokb_lab_2.application.data;

import java.util.Date;

public class DefaultDischargeData implements DischargeData {
    private int id, company, usedTarget;
    private String name;
    private Date date;
    private double diameter, minimalWaterSpeed, wastewaterConsumption,
            angle, distanceToSurface, distanceToShore;

    public DefaultDischargeData(int id, int company, int usedTarget, String name, Date date, double diameter,
                                double minimalWaterSpeed, double wastewaterConsumption, double angle,
                                double distanceToSurface, double distanceToShore) {
        this.id = id;
        this.company = company;
        this.usedTarget = usedTarget;
        this.name = name;
        this.date = date;
        this.diameter = diameter;
        this.minimalWaterSpeed = minimalWaterSpeed;
        this.wastewaterConsumption = wastewaterConsumption;
        this.angle = angle;
        this.distanceToSurface = distanceToSurface;
        this.distanceToShore = distanceToShore;
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
        this.id = id;
    }

    @Override
    public void setCompany(int company) {
        this.company = company;
    }

    @Override
    public void setUsedTarget(int usedTarget) {
        this.usedTarget = usedTarget;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    @Override
    public void setMinimalWaterSpeed(double minimalWaterSpeed) {
        this.minimalWaterSpeed = minimalWaterSpeed;
    }

    @Override
    public void setWastewaterConsumption(double wastewaterConsumption) {
        this.wastewaterConsumption = wastewaterConsumption;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public void setDistanceToSurface(double distanceToSurface) {
        this.distanceToSurface = distanceToSurface;
    }

    @Override
    public void setDistanceToShore(double distanceToShore) {
        this.distanceToShore = distanceToShore;
    }
}
