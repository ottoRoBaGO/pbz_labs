package tech.ottorobago.dokb_lab_2.application;

import tech.ottorobago.dokb_lab_2.application.data.TargetData;

import java.util.List;

public interface RetrieveTargetUseCase {
    List<TargetData> retrieveAllTargets() throws ApplicationException;
}
