package gr.wind.smartcare.queryFromRemedy;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranslateProtocols
{
    private Pattern pattern;
    private Map<String, String> protocolIds;

    public TranslateProtocols(AppConfig appConfig)
    {
        //String regexFindCode = "(\\d+)(\\(\\d\\))?\\:(\\d+)";
        String regexFindCode = "(\\d+)(\\(\\d\\))?\\:([^:;]+)";
        pattern = Pattern.compile(regexFindCode, Pattern.CASE_INSENSITIVE);
        protocolIds = appConfig.getProtocolIds();
    }

    public String translate(String codes)
    {
        Matcher matcher = pattern.matcher(codes);

        StringBuilder sb = new StringBuilder();
        while (matcher.find())
        {
            String key = matcher.group(1);
            String key_attribute = matcher.group(2);
            String value = matcher.group(3);

            // Translate key
            if (protocolIds.containsKey(key))
            {
                sb.append(protocolIds.get(key));
            }
            else
            {
                sb.append(String.format("Unknown <%s>", key));
            }

            // Insert key attribute
            if (key_attribute != null)
            {
                sb.append(key_attribute);
            }

            // Insert value part
            sb.append(String.format(":%s;", value));
        }

        // Cleanup
        String output_codes = sb.toString();
        if (output_codes != null && output_codes != "") {
            output_codes = output_codes.substring(0, output_codes.length() - 1);
        }

        return output_codes;
    }
}
