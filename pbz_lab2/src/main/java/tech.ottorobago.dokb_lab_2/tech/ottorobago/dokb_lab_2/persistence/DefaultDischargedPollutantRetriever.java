package tech.ottorobago.dokb_lab_2.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.domain.DischargedPollutant;
import tech.ottorobago.dokb_lab_2.domain.factories.DischargedPollutantFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultDischargedPollutantRetriever implements DischargedPollutantRetriever {
    private Connection connection;
    private DischargedPollutantFactory dischargedPollutantFactory;

    private static final Logger logger = LogManager.getLogger(DefaultClassifiedPollutantRetriever.class.getName());

    public DefaultDischargedPollutantRetriever(Connection connection, DischargedPollutantFactory dischargedPollutantFactory) {
        this.connection = connection;
        this.dischargedPollutantFactory = dischargedPollutantFactory;
    }

    @Override
    public DischargedPollutant loadDischargedPollutant(int id) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT `id`, `discharge`, `pollutant`," +
                    " `concentration`, `ncc`, `background_concentration`, `mpc`" +
                    " FROM `discharge_pollutants` WHERE `discharge` = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return convertResultSetToDischargedPollutant(resultSet);
            else
                throw new PersistenceException("No discharged pollutant with such id was found");
        } catch (SQLException exc) {
            String mes = "Unable to retrieve data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    @Override
    public List<DischargedPollutant> loadDischargedPollutantByDischarge(int discharge) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT `id`, `discharge`, `pollutant`," +
                    " `concentration`, `ncc`, `background_concentration`, `mpc`" +
                    " FROM `discharge_pollutants` WHERE `discharge` = ?;");
            statement.setInt(1, discharge);
            ResultSet resultSet = statement.executeQuery();

            List<DischargedPollutant> dischargedPollutants = new ArrayList<>();

            while(resultSet.next()) {
                dischargedPollutants.add(convertResultSetToDischargedPollutant(resultSet));
            }

            return dischargedPollutants;
        } catch (SQLException exc) {
            String mes = "Unable to retrieve data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    private DischargedPollutant convertResultSetToDischargedPollutant(ResultSet resultSet) throws SQLException {
        return dischargedPollutantFactory.createDischargedPollutant(resultSet.getInt("id"),
                resultSet.getInt("discharge"), resultSet.getInt("pollutant"),
                resultSet.getDouble("concentration"), resultSet.getDouble("ncc"),
                resultSet.getDouble("background_concentration"), resultSet.getDouble("mpc"));
    }
}
