package gr.wind.smartcare.queryFromRemedy;

import java.io.*;
import java.util.*;

public class PropertiesFileHelper {
    public static Map<String, String> loadPropertiesAsMap(String propFileName) throws Exception
    {
        Map<String, String> propertiesMap = new HashMap<String, String>();
        try (InputStream inputStream = new FileInputStream(propFileName)  )
        {
            Properties prop = new Properties();
            prop.load(inputStream);

            for (Object propertyName : prop.keySet() )
            {
                String propertyValue = prop.getProperty( String.valueOf(propertyName) );
                propertiesMap.put(String.valueOf(propertyName), propertyValue);
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return propertiesMap;
    }
}
