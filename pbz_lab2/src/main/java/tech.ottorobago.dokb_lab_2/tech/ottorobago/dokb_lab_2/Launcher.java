package tech.ottorobago.dokb_lab_2;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.*;
import tech.ottorobago.dokb_lab_2.application.*;
import tech.ottorobago.dokb_lab_2.application.data.*;
import tech.ottorobago.dokb_lab_2.domain.*;
import tech.ottorobago.dokb_lab_2.persistence.*;
import tech.ottorobago.dokb_lab_2.ui.Command;
import tech.ottorobago.dokb_lab_2.ui.DefaultMainLoop;
import tech.ottorobago.dokb_lab_2.ui.MainLoop;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.*;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.controllers.*;
import tech.ottorobago.dokb_lab_2.ui.stdcommands.views.*;

import java.sql.Connection;
import java.sql.SQLException;

public class Launcher {
    private final static Logger logger = LogManager.getLogger(Launcher.class.getName());
    private final static String PROGRAM_GREETINGS =
            "Welcome to dokb_lab_2!\n" +
                    "Available commands:\n" +
                    "create (company|discharge|classified_pollutant|discharged_pollutant)\n" +
                    "edit (company|discharge|classified_pollutant|discharged_pollutant) <id>\n" +
                    "remove (company|discharge|classified_pollutant|discharged_pollutant) <id>\n" +
                    "list (targets|pollutants|companies|discharges (<company_id>|<since_date> <until_date>)|\n..." +
                    "...classified_pollutants <company>|discharged_pollutants <company>) // date format: dd/MM/yyyy\n" +
                    "show discharge <id>";

    public static void main(String... args) {
        logger.debug("Entered the program");

        //connecting to MySQL
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName("dokb_lab_2_db");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setServerName("localhost");

        try {
            Connection connection = dataSource.getConnection();

            //composing all needed objects

            //list command
            PollutantRetriever pollutantRetriever = new DefaultPollutantRetriever(connection, DefaultPollutant::new);
            RetrievePollutantUseCase retrievePollutantUseCase = new DefaultRetrievePollutantUseCase(pollutantRetriever,
                    DefaultPollutantData::new);

            TargetRetriever targetRetriever = new DefaultTargetRetriever(connection, DefaultTarget::new);
            RetrieveTargetUseCase retrieveTargetUseCase = new DefaultRetrieveTargetUseCase(targetRetriever,
                    DefaultTargetData::new);

            CompanyRetriever companyRetriever = new DefaultCompanyRetriever(connection, DefaultCompany::new);
            RetrieveCompanyUseCase retrieveCompanyUseCase = new DefaultRetrieveCompanyUseCase(companyRetriever,
                    DefaultCompanyData::new);

            DischargeRetriever dischargeRetriever = new DefaultDischargeRetriever(connection, DefaultDischarge::new);
            RetrieveDischargeUseCase retrieveDischargeUseCase = new DefaultRetrieveDischargeUseCase(dischargeRetriever,
                    DefaultDischargeData::new);

            ClassifiedPollutantRetriever classifiedPollutantRetriever = new DefaultClassifiedPollutantRetriever(connection,
                    DefaultClassifiedPollutant::new);
            RetrieveClassifiedPollutantUseCase retrieveClassifiedPollutantUseCase
                    = new DefaultRetrieveClassifiedPollutantUseCase(classifiedPollutantRetriever,
                    DefaultClassifiedPollutantData::new);

            DischargedPollutantRetriever dischargedPollutantRetriever = new DefaultDischargedPollutantRetriever(connection,
                    DefaultDischargedPollutant::new);
            RetrieveDischargedPollutantUseCase retrieveDischargedPollutantUseCase
                    = new DefaultRetrieveDischargedPollutantUseCase(dischargedPollutantRetriever,
                    DefaultDischargedPollutantData::new);

            ListCommandView listCommandView = new DefaultListCommandView();
            ListCommandController listCommandController = new DefaultListCommandController(listCommandView, retrieveTargetUseCase,
                    retrievePollutantUseCase, retrieveCompanyUseCase, retrieveDischargeUseCase,
                    retrieveClassifiedPollutantUseCase, retrieveDischargedPollutantUseCase);
            Command listCommand = new ListCommand(listCommandController);

            //show command
            ShowCommandView showCommandView = new DefaultShowCommandView();
            ShowCommandController showCommandController = new DefaultShowCommandController(showCommandView, retrieveDischargeUseCase);
            Command showCommand = new ShowCommand(showCommandController);

            //remove command
            CompanyPlacer companyPlacer = new DefaultCompanyPlacer(connection);
            RemoveCompanyUseCase removeCompanyUseCase = new DefaultRemoveCompanyUseCase(companyPlacer);

            DischargePlacer dischargePlacer = new DefaultDischargePlacer(connection);
            RemoveDischargeUseCase dischargeUseCase = new DefaultRemoveDischargeUseCase(dischargePlacer);

            ClassifiedPollutantPlacer classifiedPollutantPlacer = new DefaultClassifiedPollutantPlacer(connection);
            RemoveClassifiedPollutantUseCase removeClassifiedPollutantUseCase
                    = new DefaultRemoveClassifiedPollutantUseCase(classifiedPollutantPlacer);

            DischargedPollutantPlacer dischargedPollutantPlacer = new DefaultDischargedPollutantPlacer(connection);
            RemoveDischargedPollutantUseCase dischargedPollutantUseCase
                    = new DefaultRemoveDischargedPollutantUseCase(dischargedPollutantPlacer);

            RemoveCommandView removeCommandView = new DefaultRemoveCommandView();
            RemoveCommandController removeCommandController = new DefaultRemoveCommandController(removeCommandView,
                    removeCompanyUseCase, dischargeUseCase, removeClassifiedPollutantUseCase, dischargedPollutantUseCase);

            Command removeCommand = new RemoveCommand(removeCommandController);

            //create command
            CreateCompanyUseCase createCompanyUseCase = new DefaultCreateCompanyUseCase(companyPlacer,
                    new DefaultCompanyDataToCompanyConverter(DefaultCompany::new));
            CreateDischargeUseCase createDischargeUseCase = new DefaultCreateDischargeUseCase(dischargePlacer,
                    new DefaultDischargeDataToDischargeConverter(DefaultDischarge::new));
            CreateClassifiedPollutantUseCase createClassifiedPollutantUseCase
                    = new DefaultCreateClassifiedPollutantUseCase(classifiedPollutantPlacer,
                    new DefaultClassifiedPollutantDataToClassifiedPollutantConverter(DefaultClassifiedPollutant::new));
            CreateDischargedPollutantUseCase createDischargedPollutantUseCase
                    = new DefaultCreateDischargedPollutantUseCase(dischargedPollutantPlacer,
                    new DefaultDischargedPollutantDataToDischargedPollutantConverter(DefaultDischargedPollutant::new));

            CreateCommandView createCommandView = new DefaultCreateCommandView();
            CreateCommandController createCommandController = new DefaultCreateCommandController(createCommandView,
                    createCompanyUseCase, createDischargeUseCase, createClassifiedPollutantUseCase,
                    createDischargedPollutantUseCase, DefaultCompanyData::new, DefaultDischargeData::new,
                    DefaultClassifiedPollutantData::new, DefaultDischargedPollutantData::new);

            Command createCommand = new CreateCommand(createCommandController);

            //edit command
            ChangeCompanyUseCase changeCompanyUseCase = new DefaultChangeCompanyUseCase(companyPlacer,
                    new DefaultCompanyDataToCompanyConverter(DefaultCompany::new));
            ChangeDischargeUseCase changeDischargeUseCase = new DefaultChangeDischargeUseCase(dischargePlacer,
                    new DefaultDischargeDataToDischargeConverter(DefaultDischarge::new));
            ChangeClassifiedPollutantUseCase changeClassifiedPollutantUseCase
                    = new DefaultChangeClassifiedPollutantUseCase(classifiedPollutantPlacer,
                    new DefaultClassifiedPollutantDataToClassifiedPollutantConverter(DefaultClassifiedPollutant::new));
            ChangeDischargedPollutantUseCase changeDischargedPollutantUseCase
                    = new DefaultChangeDischargedPollutantUseCase(dischargedPollutantPlacer,
                    new DefaultDischargedPollutantDataToDischargedPollutantConverter(DefaultDischargedPollutant::new));

            EditCommandView editCommandView = new DefaultEditCommandView(new DefaultCreateCommandView());
            EditCommandController editCommandController = new DefaultEditCommandController(editCommandView,
                    changeCompanyUseCase, changeDischargeUseCase, changeClassifiedPollutantUseCase,
                    changeDischargedPollutantUseCase, retrieveCompanyUseCase, retrieveDischargeUseCase,
                    retrieveClassifiedPollutantUseCase, retrieveDischargedPollutantUseCase);

            Command editCommand = new EditCommand(editCommandController);

            //main loop
            MainLoop mainLoop = new DefaultMainLoop();
            mainLoop.addCommand(listCommand);
            mainLoop.addCommand(showCommand);
            mainLoop.addCommand(removeCommand);
            mainLoop.addCommand(createCommand);
            mainLoop.addCommand(editCommand);

            System.out.println(PROGRAM_GREETINGS);

            mainLoop.run();
        } catch (SQLException exc) {
            logger.error("Unable to connect to MySQL", exc);
        }
    }
}
