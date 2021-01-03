package tech.ottorobago.dokb_lab_2.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.domain.Company;
import tech.ottorobago.dokb_lab_2.domain.Discharge;
import tech.ottorobago.dokb_lab_2.domain.factories.DischargeFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefaultDischargeRetriever implements DischargeRetriever {
    private Connection connection;
    private DischargeFactory dischargeFactory;

    private static final Logger logger = LogManager.getLogger(DefaultDischargeRetriever.class.getName());

    public DefaultDischargeRetriever(Connection connection, DischargeFactory dischargeFactory) {
        this.connection = connection;
        this.dischargeFactory = dischargeFactory;
    }

    @Override
    public Discharge loadDischarge(int id) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT `id`, `name`, UNIX_TIMESTAMP(`date`) AS `time`," +
                    " `company`, `diameter`, `minimal_water_speed`, `wastewater_consumption`, `angle`," +
                    " `distance_to_surface`, `distance_to_shore`, `used_target` FROM `discharges` WHERE `id` = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return convertResultSetToDischarge(resultSet);
            else
                throw new PersistenceException("No discharge with such id was found");
        } catch (SQLException exc) {
            String mes = "Unable to retrieve data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    @Override
    public List<Discharge> loadDischargesByCompany(int company) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT `id`, `name`, UNIX_TIMESTAMP(`date`) AS `time`," +
                    " `company`, `diameter`, `minimal_water_speed`, `wastewater_consumption`, `angle`," +
                    " `distance_to_surface`, `distance_to_shore`, `used_target` FROM `discharges` WHERE `company` = ?;");
            statement.setInt(1, company);
            ResultSet resultSet = statement.executeQuery();

            List<Discharge> discharges = new ArrayList<>();

            while (resultSet.next())
                discharges.add(convertResultSetToDischarge(resultSet));

            return discharges;
        } catch (SQLException exc) {
            String mes = "Unable to retrieve data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    @Override
    public List<Discharge> loadDischargesByCompanyAndTime(int company, Date since, Date until) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT `id`, `name`, UNIX_TIMESTAMP(`date`) AS `time`," +
                    " `company`, `diameter`, `minimal_water_speed`, `wastewater_consumption`, `angle`," +
                    " `distance_to_surface`, `distance_to_shore`, `used_target` FROM `discharges`" +
                    " WHERE `company` = ? AND `date` BETWEEN ? AND ?;");
            statement.setInt(1, company);
            statement.setDate(2, new java.sql.Date(since.getTime()));
            statement.setDate(3, new java.sql.Date(until.getTime()));

            ResultSet resultSet = statement.executeQuery();

            List<Discharge> discharges = new ArrayList<>();

            while (resultSet.next())
                discharges.add(convertResultSetToDischarge(resultSet));

            return discharges;
        } catch (SQLException exc) {
            String mes = "Unable to retrieve data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    private Discharge convertResultSetToDischarge(ResultSet resultSet) throws SQLException {
        return dischargeFactory.createDischarge(resultSet.getInt("id"), resultSet.getInt("company"),
                resultSet.getInt("used_target"), resultSet.getString("name"),
                new Date(resultSet.getInt("time") * 1000L), resultSet.getDouble("diameter"),
                resultSet.getDouble("minimal_water_speed"), resultSet.getDouble("wastewater_consumption"),
                resultSet.getDouble("angle"), resultSet.getDouble("distance_to_surface"),
                resultSet.getDouble("distance_to_shore"));
    }
}
