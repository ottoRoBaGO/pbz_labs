package tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers;

import tech.ottorobago.dokb_lab_2.ui.Controller;

/*actually it violates SRP and ISP but this is just a lab and i'm so lazy.
 * So in a good way this must be split.*/
public interface RemoveCommandController extends Controller {
    void removeCompany(int id);

    void removeDischarge(int id);

    void removeClassifiedPollutant(int id);

    void removeDischargedPollutant(int id);
}
