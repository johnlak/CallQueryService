package gr.wind.smartcare.queryFromRemedy;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

public class AppConfig
{
    private @Getter String log_config;
    private @Getter String logger_file_name;
    private @Getter String register_query;
    private @Getter String call_query;
    private @Getter String epoch_day;
    private @Getter String from_epoch_time;
    private @Getter String to_epoch_time;
    private @Getter String telephone;
    private @Getter String saved_csv_filename;
    private @Getter String saved_sql_filename;
    private @Getter String query_shell_script;
    private @Getter long timestamp_plus_minus_minutes;
    private @Getter String authenticated_users;
    private @Getter String cli_regex_validation_match;
    private @Getter String protocol_ids_config;
    private @Getter Map<String, String> protocolIds;

    public AppConfig(String propertiesFilename) throws Exception
    {
        Map<String, String> configurationProperties = new HashMap<String, String>();
        protocolIds = new HashMap<>();
        try {
            configurationProperties = PropertiesFileHelper.loadPropertiesAsMap(propertiesFilename);

            log_config = configurationProperties.get("log_config");
            logger_file_name = configurationProperties.get("logger_file_name");
            register_query = configurationProperties.get("register_query");
            call_query = configurationProperties.get("call_query");
            epoch_day = configurationProperties.get("epoch_day");
            from_epoch_time = configurationProperties.get("from_epoch_time");
            to_epoch_time = configurationProperties.get("to_epoch_time");
            telephone = configurationProperties.get("telephone");
            saved_csv_filename = configurationProperties.get("saved_csv_filename");
            saved_sql_filename = configurationProperties.get("saved_sql_filename");
            query_shell_script = configurationProperties.get("query_shell_script");
            timestamp_plus_minus_minutes = Long.parseLong(configurationProperties.get("timestamp_plus_minus_minutes"));
            authenticated_users = configurationProperties.get("authenticated_users");
            cli_regex_validation_match = configurationProperties.get("cli_regex_validation_match");

            protocol_ids_config = configurationProperties.get("protocol_ids_config");
            protocolIds = PropertiesFileHelper.loadPropertiesAsMap(protocol_ids_config);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
