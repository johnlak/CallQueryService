package gr.wind.smartcare.queryFromRemedy;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestValidator
{
    boolean isValid;
    String nonValidReason;

    public RequestValidator(
            AppConfig appConfig,
            Log4j2Helper log4j2Helper,
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
        isValid = true;
        nonValidReason = null;
        try
        {
            // Check if fields have values
            if (userName == null || userName.isEmpty()) {
                throw new InvalidSmartcareRequestException("ERROR: userName is empty");
            }
            if (password == null || password.isEmpty()) {
                throw new InvalidSmartcareRequestException("ERROR: password is empty");
            }

            // Authenticate user
            try
            {
                VerifyUserAuthentication verifyUserAuthentication = new VerifyUserAuthentication(appConfig.getAuthenticated_users());
                boolean authenticates = verifyUserAuthentication.authenticates(userName, password);
                if (!authenticates)
                {
                    //log4j2Helper.error(String.format("Authentication exception: %s, %s %s", userName, password, appConfig.getAuthenticated_users()));
                    throw new InvalidSmartcareRequestException("ERROR: authentication failed");
                }
            }
            catch (InvalidSmartcareRequestException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                log4j2Helper.error(String.format("Exception: %s, %s", ex.toString(), sw.toString()));
                throw new InvalidSmartcareRequestException("ERROR: authentication error");
            }

            // Check if fields have values
            if (userTimestamp == null || userTimestamp.isEmpty()) {
                throw new InvalidSmartcareRequestException("ERROR: userTimestamp is empty");
            }
            else
            {
                try {
                    LocalDateTime.parse(userTimestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                }
                catch (DateTimeParseException ex)
                {
                    throw new InvalidSmartcareRequestException("ERROR: userTimestamp is invalid");
                }
            }
            if (userCli == null || userCli.isEmpty()) {
                throw new InvalidSmartcareRequestException("ERROR: userCli is empty");
            }
            else
            {
                Pattern pattern = Pattern.compile(appConfig.getCli_regex_validation_match(), Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(userCli);
                boolean matchFound = matcher.matches();
                if (!matchFound)
                {
                    throw new InvalidSmartcareRequestException("ERROR: userCli is invalid");
                }
            }
            if (requestID == null || requestID.isEmpty()) {
                throw new InvalidSmartcareRequestException("ERROR: requestID is empty");
            }
            if (requestTimestamp == null || requestTimestamp.isEmpty()) {
                throw new InvalidSmartcareRequestException("ERROR: requestTimestamp is empty");
            }
            else
            {
                try {
                    LocalDateTime.parse(requestTimestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                }
                catch (DateTimeParseException ex)
                {
                    throw new InvalidSmartcareRequestException("ERROR: requestTimestamp is invalid");
                }
            }
            if (remedyUser == null || remedyUser.isEmpty()) {
                throw new InvalidSmartcareRequestException("ERROR: remedyUser is empty");
            }
            if (systemId == null || systemId.isEmpty()) {
                throw new InvalidSmartcareRequestException("ERROR: systemId is empty");
            }
        }
        catch (InvalidSmartcareRequestException e)
        {
            isValid = false;
            nonValidReason = e.getMessage();
        }
    }
}
