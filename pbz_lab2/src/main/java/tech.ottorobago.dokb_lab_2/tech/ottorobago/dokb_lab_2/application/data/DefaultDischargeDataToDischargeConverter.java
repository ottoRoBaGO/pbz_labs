package tech.ottorobago.dokb_lab_2.application.data;

import tech.ottorobago.dokb_lab_2.domain.Discharge;
import tech.ottorobago.dokb_lab_2.domain.factories.DischargeFactory;

public class DefaultDischargeDataToDischargeConverter implements DischargeDataToDischargeConverter {
    private DischargeFactory dischargeFactory;

    public DefaultDischargeDataToDischargeConverter(DischargeFactory dischargeFactory) {
        this.dischargeFactory = dischargeFactory;
    }

    @Override
    public Discharge convertDischargeDataToDischarge(DischargeData dischargeData) {
        return dischargeFactory.createDischarge(dischargeData.getId(), dischargeData.getCompany(),
                dischargeData.getUsedTarget(), dischargeData.getName(), dischargeData.getDate(),
                dischargeData.getDiameter(), dischargeData.getMinimalWaterSpeed(),
                dischargeData.getWastewaterConsumption(), dischargeData.getAngle(),
                dischargeData.getDistanceToSurface(), dischargeData.getDistanceToShore());
    }
}
