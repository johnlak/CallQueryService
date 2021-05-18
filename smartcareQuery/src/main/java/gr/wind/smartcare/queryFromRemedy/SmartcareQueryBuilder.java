package gr.wind.smartcare.queryFromRemedy;

import java.time.LocalDateTime;
import java.util.UUID;

public class SmartcareQueryBuilder
{
    public enum QueryType {register_query, call_query};

    public static String createQueryFile(AppConfig appConfig, QueryType queryType, String telephone, LocalDateTime timestamp, UUID uuid) throws Exception
    {
        try {
            String queryContent = "";
            if (queryType == QueryType.register_query) {
                queryContent = FileHelper.readFile(appConfig.getRegister_query());
            } else if (queryType == QueryType.call_query) {
                queryContent = FileHelper.readFile(appConfig.getCall_query());
            }

            String epochDay = String.valueOf( EpochHelper.epochDay(timestamp) );
            LocalDateTime fromTime = timestamp.minusMinutes(appConfig.getTimestamp_plus_minus_minutes());
            LocalDateTime toTime = timestamp.plusMinutes(appConfig.getTimestamp_plus_minus_minutes());
            long fromTimeEpoch = EpochHelper.getEpochSeconds(fromTime);
            long toTimeEpoch = EpochHelper.getEpochSeconds(toTime);

            queryContent = queryContent.replace(appConfig.getEpoch_day(), epochDay);
            queryContent = queryContent.replace(appConfig.getFrom_epoch_time(), String.valueOf(fromTimeEpoch) );
            queryContent = queryContent.replace(appConfig.getTo_epoch_time(), String.valueOf(toTimeEpoch) );
            queryContent = queryContent.replace(appConfig.getTelephone(), telephone );

            String filename = appConfig.getSaved_sql_filename().replace("##UUID##", uuid.toString());
            FileHelper.writeFile(filename, queryContent);

            return filename;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public static String getQueryFilename(AppConfig appConfig, UUID uuid)
    {
        String filename = appConfig.getSaved_sql_filename().replace("##UUID##", uuid.toString());
        return filename;
    }

    public static String getCsvFilename(AppConfig appConfig, UUID uuid)
    {
        String filename = appConfig.getSaved_csv_filename().replace("##UUID##", uuid.toString());
        return filename;
    }

    public static String getBashScriptCommand(AppConfig appConfig, UUID uuid)
    {
        String command = appConfig.getQuery_shell_script();
        command = command.replace("##SQLFILE##", getQueryFilename(appConfig,uuid));
        command = command.replace("##CSVFILE##", getCsvFilename(appConfig,uuid));
        return command;
    }
}
