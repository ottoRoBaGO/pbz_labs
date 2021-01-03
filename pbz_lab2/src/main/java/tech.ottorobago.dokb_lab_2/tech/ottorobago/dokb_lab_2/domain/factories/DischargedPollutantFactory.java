package tech.ottorobago.dokb_lab_2.domain.factories;

import tech.ottorobago.dokb_lab_2.domain.DischargedPollutant;

public interface DischargedPollutantFactory {
    DischargedPollutant createDischargedPollutant(int id, int discharge, int pollutant, double concentration, double ncc,
                                                  double backgroundConcentration, double mpc);
}
