package tech.ottorobago.dokb_lab_2.domain;

import java.util.Date;

public interface Discharge {
    int getId();

    int getCompany();

    int getUsedTarget();

    String getName();

    Date getDate();

    double getDiameter();

    double getMinimalWaterSpeed();

    double getWastewaterConsumption();

    double getAngle();

    double getDistanceToSurface();

    double getDistanceToShore();

    /**this method is not supposed to be used with purpose of change the id field
     * in a persistence storage, it's supposed to change the id field only in context of java*/
    void setId(int id);

    void setCompany(int company);

    void setUsedTarget(int usedTarget);

    void setName(String name);

    void setDate(Date date);

    void setDiameter(double diameter);

    void setMinimalWaterSpeed(double minimalWaterSpeed);

    void setWastewaterConsumption(double wastewaterConsumption);

    void setAngle(double angle);

    void setDistanceToSurface(double distanceToSurface);

    void setDistanceToShore(double distanceToShore);
}
