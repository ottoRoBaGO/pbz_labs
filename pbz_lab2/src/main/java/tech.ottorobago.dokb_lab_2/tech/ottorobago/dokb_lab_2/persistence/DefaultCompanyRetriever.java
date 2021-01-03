package tech.ottorobago.dokb_lab_2.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.ottorobago.dokb_lab_2.domain.Company;
import tech.ottorobago.dokb_lab_2.domain.factories.CompanyFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultCompanyRetriever implements CompanyRetriever {
    private Connection connection;
    private CompanyFactory companyFactory;

    private static final Logger logger = LogManager.getLogger(DefaultCompanyRetriever.class.getName());

    public DefaultCompanyRetriever(Connection connection, CompanyFactory companyFactory) {
        this.connection = connection;
        this.companyFactory = companyFactory;
    }

    @Override
    public Company loadCompany(int id) throws PersistenceException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT `id`, `name` FROM `companies` WHERE `id` = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return convertResultSetToCompany(resultSet);
            else
                throw new PersistenceException("No company with such id was found");
        } catch (SQLException exc) {
            String mes = "Unable to retrieve data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }

    Company convertResultSetToCompany(ResultSet resultSet) throws SQLException {
        return companyFactory.createCompany(resultSet.getInt("id"), resultSet.getString("name"));
    }

    @Override
    public List<Company> loadAllCompanies() throws PersistenceException {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT `id`, `name` FROM `companies`;");

            List<Company> companies = new ArrayList<>();

            while (resultSet.next())
                companies.add(convertResultSetToCompany(resultSet));

            return companies;
        } catch (SQLException exc) {
            String mes = "Unable to retrieve data from MySQL";

            logger.error(mes, exc);

            throw new PersistenceException(mes);
        }
    }
}
