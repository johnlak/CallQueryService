package gr.wind.smartcare.queryFromRemedy;

import lombok.Getter;
import java.nio.file.Paths;
import java.util.UUID;

public class ConfigurationLoader
{
    private @Getter AppConfig appConfig;
    private @Getter Log4j2Helper log4j2Helper;
    private @Getter UUID uuid;

    public ConfigurationLoader() throws Exception
    {
        uuid = UUID.randomUUID();

        String confFilePath = Paths.get(System.getProperty("user.dir"),"config.properties").toString();
        System.out.println("Config File: " + confFilePath);
        //System.out.println(System.getenv());
        //System.out.println(System.getProperties());

        try
        {
            appConfig = new AppConfig(confFilePath);
            System.out.println("Log Config File: " + appConfig.getLog_config());
            log4j2Helper = new Log4j2Helper(appConfig.getLog_config());
            log4j2Helper.makeLogger(appConfig.getLogger_file_name(), uuid);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}
