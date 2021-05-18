package gr.wind.smartcare.queryFromRemedy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class CarbonQueryExecution
{
    private AppConfig appConfig;
    private Log4j2Helper log4j2Helper;
    private UUID uuid;

    public CarbonQueryExecution(
            AppConfig appConfig,
            Log4j2Helper log4j2Helper,
            UUID uuid
    )
    {
        this.appConfig = appConfig;
        this.log4j2Helper = log4j2Helper;
        this.uuid = uuid;
    }

    public List<Map<String, String>> run(
            SmartcareQueryBuilder.QueryType queryType,
            String userName,
            String password,
            String userTimestamp,
            String userCli,
            String requestID,
            String requestTimestamp,
            String remedyUser,
            String systemId
    ) throws Exception
    {
        List<Map<String, String>> csvData = new ArrayList<>();
        try
        {
            // Log request details
            log4j2Helper.info(
                String.format(
                    "UserName: %s, UserTimestamp: %s, UserCli: %s, RequestID: %s, RequestTimestamp: %s, RemedyUser: %s, SystemId: %s",
                    userName, userTimestamp, userCli, requestID, requestTimestamp, remedyUser, systemId
                )
            );
            LocalDateTime dateTime = EpochHelper.getDateTime(userTimestamp);
            log4j2Helper.info(String.format("Request epoch timestamp: %s", String.valueOf(EpochHelper.getEpochSeconds(dateTime))));
            log4j2Helper.info(String.format("Request query type: %s", queryType.toString()));

            // Create SQL request from the templates
            String queryFilename = SmartcareQueryBuilder.createQueryFile(appConfig, queryType, userCli, dateTime, uuid);
            log4j2Helper.info(String.format("Created query: %s", queryFilename));

            // Run bash script and print output
            String queryCommand = SmartcareQueryBuilder.getBashScriptCommand(appConfig, uuid);
            log4j2Helper.info("Starting Query Execution");
            log4j2Helper.info(String.format("command: %s", queryCommand));
            String commandOutput = CommandLineProcessExecutor.run(queryCommand);
            log4j2Helper.info("Completed Query Execution");
            log4j2Helper.info(commandOutput);

            // Read CSV data and parse them into a List<Map<String, String>> object
            String csvFilename = SmartcareQueryBuilder.getCsvFilename(appConfig, uuid);
            log4j2Helper.info(String.format("Reading CSV: %s", csvFilename));
            csvData = CSVHelper.readAllAsMap(csvFilename);
        }
        catch (Exception e)
        {
            throw e;
        }

        return csvData;
    }
}

