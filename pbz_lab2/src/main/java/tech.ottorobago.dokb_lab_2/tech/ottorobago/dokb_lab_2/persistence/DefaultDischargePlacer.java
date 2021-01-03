package tech.ottorobago.dokb_lab_2.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.domain.Discharge;

import java.sql.*;

public class DefaultDischargePlacer implements DischargePlacer {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(DefaultDischargePlacer.class.getName());

    public DefaultDischargePlacer(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Discharge createDischarge(Discharge discharge) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `discharges` (`name`, `date`, `company`," +
                    " `diameter`, `minimal_water_speed`, `wastewater_consumption`, `angle`, `distance_to_surface`," +
                    " `distance_to_shore`, `used_target`) VALUES (?, ?, ?, ?, ?, ? ,?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, discharge.getName());
            statement.setDate(2, new Date(discharge.getDate().getTime()));
            statement.setInt(3, discharge.getCompany());
            statement.setDouble(4, discharge.getDiameter());
            statement.setDouble(5, discharge.getMinimalWaterSpeed());
            statement.setDouble(6, discharge.getWastewaterConsumption());
            statement.setDouble(7, discharge.getAngle());
            statement.setDouble(8, discharge.getDistanceToSurface());
            statement.setDouble(9, discharge.getDistanceToShore());
            statement.setInt(10, discharge.getUsedTarget());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);

                discharge.setId(id);

                return discharge;
            } else
                throw new PersistenceException("Unable to retrieve the last inserted id");
        } catch (SQLException exc) {
            String mes = "Unable to insert data into MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    @Override
    public void saveDischarge(Discharge discharge) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `discharges` SET `name` = ?, `date` = ?," +
                    " `company` = ?, `diameter` = ?, `minimal_water_speed` = ?, `wastewater_consumption` = ?," +
                    " `angle` = ?, `distance_to_surface` = ?, `distance_to_shore` = ?, `used_target` = ? WHERE `id` = ?;");
            statement.setString(1, discharge.getName());
            statement.setDate(2, new Date(discharge.getDate().getTime()));
            statement.setInt(3, discharge.getCompany());
            statement.setDouble(4, discharge.getDiameter());
            statement.setDouble(5, discharge.getMinimalWaterSpeed());
            statement.setDouble(6, discharge.getWastewaterConsumption());
            statement.setDouble(7, discharge.getAngle());
            statement.setDouble(8, discharge.getDistanceToSurface());
            statement.setDouble(9, discharge.getDistanceToShore());
            statement.setInt(10, discharge.getUsedTarget());
            statement.setInt(11, discharge.getId());

            statement.executeUpdate();
        } catch (SQLException exc) {
            String mes = "Unable to update data in MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    @Override
    public void removeDischarge(int id) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `discharges` WHERE `id` = ?;");

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException exc) {
            String mes = "Unable to remove data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }
}
