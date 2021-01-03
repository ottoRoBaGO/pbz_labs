package tech.ottorobago.dokb_lab_2.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.domain.Target;
import tech.ottorobago.dokb_lab_2.domain.factories.TargetFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DefaultTargetRetriever implements TargetRetriever {
    private Connection connection;
    private Logger logger = LogManager.getLogger(DefaultTargetRetriever.class.getName());
    private TargetFactory targetFactory;

    public DefaultTargetRetriever(Connection connection, TargetFactory targetFactory) {
        this.connection = connection;
        this.targetFactory = targetFactory;
    }

    @Override
    public List<Target> loadAllTargets() throws PersistenceException {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT `id`, `name`, `distance` FROM `targets`;");

            List<Target> result = new ArrayList<>();

            while (resultSet.next()) {
                result.add(targetFactory.createTarget(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getDouble("distance")));
            }

            return result;
        } catch (SQLException exc) {
            String mes = "Unable to retrieve data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }
}
