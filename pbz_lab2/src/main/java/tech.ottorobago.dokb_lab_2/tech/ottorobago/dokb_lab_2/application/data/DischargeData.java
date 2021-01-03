package tech.ottorobago.dokb_lab_2.application.data;

import java.util.Date;

public interface DischargeData {
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
