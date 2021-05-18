package gr.wind.smartcare.queryFromRemedy;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RegisterQuery {
    public RegisterQueryResponse makeRegisterQuery(
        String userName,
        String password,
        String userTimestamp,
        String userCli,
        String requestID,
        String requestTimestamp,
        String remedyUser,
        String systemId
    )
    {
        RegisterQueryResponse response = new RegisterQueryResponse();
        response.queryResponseData = new ArrayList<>();
        response.errorOccurred = false;
        response.errorMessage = null;
        response.requestId = null;
        Log4j2Helper log4j2Helper = null;

        try
        {
            // Load WebService config.properties and logger
            ConfigurationLoader myconfig = new ConfigurationLoader();
            UUID uuid = myconfig.getUuid();
            response.requestId = uuid.toString();
            AppConfig appConfig = myconfig.getAppConfig();
            log4j2Helper = myconfig.getLog4j2Helper();

            // Validate request
            RequestValidator requestValidity = new RequestValidator(
                appConfig,
                log4j2Helper,
                userName,
                password,
                userTimestamp,
                userCli,
                requestID,
                requestTimestamp,
                remedyUser,
                systemId
            );

            // If not a valid request stop
            if (!requestValidity.isValid)
            {
                throw new InvalidSmartcareRequestException(requestValidity.nonValidReason);
            }

            // Query Carbon
            CarbonQueryExecution carbonQueryExecution = new CarbonQueryExecution(appConfig, log4j2Helper, uuid);
            List<Map<String, String>> csvData = carbonQueryExecution.run(
                    SmartcareQueryBuilder.QueryType.register_query, userName, password,
                    userTimestamp, userCli, requestID, requestTimestamp, remedyUser, systemId
            );

            // Convert response data into the response model
            List<RegisterQueryResponseModel> queryResponseData = RegisterQueryResponseModel.getRegisterResponseFromCsv(csvData);

            // Fill response data
            response.queryResponseData = queryResponseData;
        }
        catch (InvalidSmartcareRequestException e)
        {
            if ( log4j2Helper != null)
            {
                log4j2Helper.error(String.format("Authentication exception: %s", e.getMessage()));
            }
            response.errorMessage = e.getMessage();
            response.errorOccurred = true;
        }
        catch (Exception e)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            if ( log4j2Helper != null)
            {
                log4j2Helper.error(String.format("Exception: %s, %s", e.toString(), sw.toString()));
            }
            response.errorMessage = String.format("Exception: %s, %s", e.toString(), sw.toString() );
            response.errorOccurred = true;
        }

        return response;
    }
}