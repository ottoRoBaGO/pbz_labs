package tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers;

import tech.ottorobago.dokb_lab_2.ui.Controller;

/*actually it violates SRP and ISP but this is just a lab and i'm so lazy.
 * So in a good way this must be split.*/
public interface EditCommandController extends Controller {
    void editCompany(int id);

    void editDischarge(int id);

    void editClassifiedPollutant(int id);

    void editDischargedPollutant(int id);
}
