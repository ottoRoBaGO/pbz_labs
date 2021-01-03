package tech.ottorobago.dokb_lab_2.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.domain.ClassifiedPollutant;
import tech.ottorobago.dokb_lab_2.domain.factories.ClassifiedPollutantFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultClassifiedPollutantRetriever implements ClassifiedPollutantRetriever {
    private Connection connection;
    private ClassifiedPollutantFactory classifiedPollutantFactory;

    private static final Logger logger = LogManager.getLogger(DefaultClassifiedPollutantRetriever.class.getName());

    public DefaultClassifiedPollutantRetriever(Connection connection,
                                               ClassifiedPollutantFactory classifiedPollutantFactory) {
        this.connection = connection;
        this.classifiedPollutantFactory = classifiedPollutantFactory;
    }

    @Override
    public ClassifiedPollutant loadClassifiedPollutant(int id) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT `id`, `company`, `pollutant`, `danger_class`," +
                    " `lfv_group` FROM `company_pollutants_classes` WHERE `id` = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return convertResultSetToClassifiedPollutant(resultSet);
            else
                throw new PersistenceException("No classified pollutant with such id was found");
        } catch (SQLException exc) {
            String mes = "Unable to retrieve data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    @Override
    public List<ClassifiedPollutant> loadClassifiedPollutantsByCompany(int company) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT `id`, `company`, `pollutant`, `danger_class`," +
                    " `lfv_group` FROM `company_pollutants_classes` WHERE `company` = ?;");
            statement.setInt(1, company);
            ResultSet resultSet = statement.executeQuery();

            List<ClassifiedPollutant> classifiedPollutants = new ArrayList<>();

            while (resultSet.next())
                classifiedPollutants.add(convertResultSetToClassifiedPollutant(resultSet));

            return classifiedPollutants;
        } catch (SQLException exc) {
            String mes = "Unable to retrieve data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    private ClassifiedPollutant convertResultSetToClassifiedPollutant(ResultSet resultSet) throws SQLException {
        return classifiedPollutantFactory.createClassifiedPollutant(resultSet.getInt("id"),
                resultSet.getInt("company"), resultSet.getInt("pollutant"),
                resultSet.getString("danger_class"), resultSet.getString("lfv_group"));
    }
}
