package tech.ottorobago.dokb_lab_2.application.data.factories;

import tech.ottorobago.dokb_lab_2.application.data.DischargeData;

import java.util.Date;

public interface DischargeDataFactory {
    DischargeData createDischargeData(int id, int company, int usedTarget, String name, Date date, double diameter,
                                               double minimalWaterSpeed, double wastewaterConsumption, double angle,
                                               double distanceToSurface, double distanceToShore);
}
