package gr.wind.smartcare.queryFromRemedy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.util.UUID;

public class Log4j2Helper
{
    private LoggerContext context;
    private Logger logger;
    private String uuidStr;

    public Log4j2Helper(String configurationFilePath) throws Exception
    {
        try
        {
            context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
            File file = new File(configurationFilePath);
            context.setConfigLocation(file.toURI());
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public Logger getLogger()
    {
        return logger;
    }

    public void makeLogger(String loggerName, UUID uuid)
    {
        logger = context.getLogger(loggerName);
        uuidStr = uuid.toString();
    }

    public void info(String loggedText)
    {
        logger.info(String.format("%s %s", uuidStr, loggedText));
    }

    public void debug(String loggedText)
    {
        logger.debug(String.format("%s %s", uuidStr, loggedText));
    }

    public void error(String loggedText)
    {
        logger.error(String.format("%s %s", uuidStr, loggedText));
    }

    public void fatal(String loggedText)
    {
        logger.fatal(String.format("%s %s", uuidStr, loggedText));
    }

    public void warn(String loggedText)
    {
        logger.warn(String.format("%s %s", uuidStr, loggedText));
    }
}
