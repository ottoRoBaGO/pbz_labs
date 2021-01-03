package tech.ottorobago.dokb_lab_2.ui.stdcommands.views;

import tech.ottorobago.dokb_lab_2.ui.View;

/*actually it violates SRP and ISP but this is just a lab and i'm so lazy.
 * So in a good way this must be split.*/
public interface RemoveCommandView extends View {
    void showCompanyRemoved();

    void showDischargeRemoved();

    void showClassifiedPollutantRemoved();

    void showDischargedPollutantRemoved();
}
