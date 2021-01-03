package tech.ottorobago.dokb_lab_2.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.domain.Company;

import java.sql.*;

public class DefaultCompanyPlacer implements CompanyPlacer {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(DefaultCompanyPlacer.class.getName());

    public DefaultCompanyPlacer(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Company createCompany(Company company) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `companies` (`name`) VALUES (?);",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, company.getName());

            statement.executeUpdate();

            ResultSet ids = statement.getGeneratedKeys();

            if (ids.next()) {
                int id = ids.getInt(1);

                company.setId(id);

                return company;
            } else
                throw new PersistenceException("Unable to retrieve inserted id from MySQL");
        } catch (SQLException exc) {
            String mes = "Unable to insert data into MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    @Override
    public void saveCompany(Company company) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `companies` SET `name` = ? WHERE `id` = ?;");
            statement.setString(1, company.getName());
            statement.setInt(2, company.getId());

            statement.executeUpdate();
        } catch (SQLException exc) {
            String mes = "Unable to update data in MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    @Override
    public void removeCompany(int id) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `companies` WHERE `id` = ?;");
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException exc) {
            String mes = "Unable to remove data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }
}
