package tech.ottorobago.dokb_lab_2.application;

import tech.ottorobago.dokb_lab_2.application.data.DischargeData;

import java.util.Date;

public interface ChangeDischargeUseCase {
    void changeDischarge(DischargeData dischargeData) throws ApplicationException;
}
