package tech.ottorobago.dokb_lab_2.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.domain.ClassifiedPollutant;

import java.sql.*;

public class DefaultClassifiedPollutantPlacer implements ClassifiedPollutantPlacer {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(DefaultClassifiedPollutantPlacer.class.getName());

    public DefaultClassifiedPollutantPlacer(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ClassifiedPollutant createClassifiedPollutant(ClassifiedPollutant classifiedPollutant) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `company_pollutants_classes`" +
                    " (`company`, `pollutant`, `danger_class`, `lfv_group`) VALUES (?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, classifiedPollutant.getCompany());
            statement.setInt(2, classifiedPollutant.getPollutant());
            statement.setString(3, classifiedPollutant.getDangerClass());
            statement.setString(4, classifiedPollutant.getLfvGroup());

            statement.executeUpdate();

            ResultSet ids = statement.getGeneratedKeys();

            if (ids.next()) {
                int id = ids.getInt(1);

                classifiedPollutant.setId(id);

                return classifiedPollutant;
            } else
                throw new PersistenceException("Unable to retrieve the last id from MySQL");
        } catch (SQLException exc) {
            String mes = "Unable to insert data into MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    @Override
    public void saveClassifiedPollutant(ClassifiedPollutant classifiedPollutant) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `company_pollutants_classes`" +
                    " SET `danger_class` = ?, `lfv_group` = ? WHERE `id` = ?;");
            statement.setString(1, classifiedPollutant.getDangerClass());
            statement.setString(2, classifiedPollutant.getLfvGroup());
            statement.setInt(3, classifiedPollutant.getId());

            statement.executeUpdate();
        } catch (SQLException exc) {
            String mes = "Unable to update data in MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    @Override
    public void removeClassifiedPollutant(int id) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `company_pollutants_classes`" +
                    " WHERE `id` = ?;");
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException exc) {
            String mes = "Unable to remove data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }
}
