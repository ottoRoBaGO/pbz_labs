package tech.ottorobago.dokb_lab_2.application.data.factories;

import tech.ottorobago.dokb_lab_2.application.data.TargetData;

public interface TargetDataFactory {
    TargetData createTargetData(int id, String name, double distance);
}
