package tech.ottorobago.dokb_lab_2.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.domain.DischargedPollutant;

import java.sql.*;

public class DefaultDischargedPollutantPlacer implements DischargedPollutantPlacer {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(DefaultClassifiedPollutantPlacer.class.getName());

    public DefaultDischargedPollutantPlacer(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DischargedPollutant createDischargedPollutant(DischargedPollutant dischargedPollutant) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO `discharge_pollutants` (`discharge`, `pollutant`, `concentration`, `ncc`," +
                            " `background_concentration`, `mpc`) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, dischargedPollutant.getDischarge());
            statement.setInt(2, dischargedPollutant.getPollutant());
            statement.setDouble(3, dischargedPollutant.getConcentration());
            statement.setDouble(4, dischargedPollutant.getNcc());
            statement.setDouble(5, dischargedPollutant.getBackgroundConcentration());
            statement.setDouble(6, dischargedPollutant.getMpc());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);

                dischargedPollutant.setId(id);

                return dischargedPollutant;
            } else
                throw new PersistenceException("Unable to retrieve the last inserted id");
        } catch (SQLException exc) {
            String mes = "Unable to insert data into MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    @Override
    public void saveDischargedPollutant(DischargedPollutant dischargedPollutant) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE `discharge_pollutants` SET `discharge` = ?, `pollutant` = ?, `concentration` = ?, `ncc` = ?," +
                            " `background_concentration` = ?, `mpc` = ? WHERE `id` = ?;");
            statement.setInt(1, dischargedPollutant.getDischarge());
            statement.setInt(2, dischargedPollutant.getPollutant());
            statement.setDouble(3, dischargedPollutant.getConcentration());
            statement.setDouble(4, dischargedPollutant.getNcc());
            statement.setDouble(5, dischargedPollutant.getBackgroundConcentration());
            statement.setDouble(6, dischargedPollutant.getMpc());
            statement.setDouble(7, dischargedPollutant.getId());

            statement.executeUpdate();
        } catch (SQLException exc) {
            String mes = "Unable to update data in MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    @Override
    public void removeDischargedPollutant(int id) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM `discharge_pollutants` WHERE `id` = ?;");
            statement.setDouble(1, id);

            statement.executeUpdate();
        } catch (SQLException exc) {
            String mes = "Unable to remove data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }
}
