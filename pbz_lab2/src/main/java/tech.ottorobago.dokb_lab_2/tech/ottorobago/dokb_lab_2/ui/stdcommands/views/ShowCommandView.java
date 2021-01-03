package tech.ottorobago.dokb_lab_2.ui.stdcommands.views;

import tech.ottorobago.dokb_lab_2.application.data.DischargeData;
import tech.ottorobago.dokb_lab_2.ui.View;

public interface ShowCommandView extends View {
    void showDischarge(DischargeData dischargeData);
}
