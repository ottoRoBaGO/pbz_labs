package tech.ottorobago.dokb_lab_2.application.data;

import tech.ottorobago.dokb_lab_2.domain.Discharge;

public interface DischargeDataToDischargeConverter {
    Discharge convertDischargeDataToDischarge(DischargeData dischargeData);
}
