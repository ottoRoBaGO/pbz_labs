package tech.ottorobago.dokb_lab_2.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.domain.Pollutant;
import tech.ottorobago.dokb_lab_2.domain.factories.PollutantFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DefaultPollutantRetriever implements PollutantRetriever {
    private Connection connection;
    private PollutantFactory pollutantFactory;

    private Logger logger = LogManager.getLogger(DefaultPollutantRetriever.class.getName());

    public DefaultPollutantRetriever(Connection connection, PollutantFactory pollutantFactory) {
        this.connection = connection;
        this.pollutantFactory = pollutantFactory;
    }

    @Override
    public List<Pollutant> loadAllPollutants() throws PersistenceException {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT `id`, `name` FROM `pollutants`;");

            List<Pollutant> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(pollutantFactory.createPollutant(resultSet.getInt("id"), resultSet.getString("name")));
            }

            return result;
        } catch (SQLException exc) {
            String mes = "Unable to retrieve data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }
}
