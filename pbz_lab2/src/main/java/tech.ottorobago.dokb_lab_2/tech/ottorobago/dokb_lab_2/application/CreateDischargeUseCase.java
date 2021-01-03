package tech.ottorobago.dokb_lab_2.application;

import tech.ottorobago.dokb_lab_2.application.data.DischargeData;

import java.util.Date;

public interface CreateDischargeUseCase {
    /*returns data with set id*/
    DischargeData createDischarge(DischargeData dischargeData) throws ApplicationException;
}
