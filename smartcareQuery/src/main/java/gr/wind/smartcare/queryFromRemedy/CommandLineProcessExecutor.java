package gr.wind.smartcare.queryFromRemedy;

import java.io.*;

public class CommandLineProcessExecutor
{
    public static String run (String command) throws Exception
    {
        String result = null;
        try
        {
            Runtime r = Runtime.getRuntime();

            Process p = r.exec(command);

            BufferedReader in =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                if (result == null)
                {
                    result = inputLine;
                }
                else
                {
                    result += System.lineSeparator() + inputLine;
                }
            }
            in.close();
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }
}
