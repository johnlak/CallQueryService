package gr.wind.smartcare.queryFromRemedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Query 2
public class CallQueryResponseModel
{
    public String sn;
    public String startTime;
    public String callingNumber;
    public String calledNumber;
    public String callDuration;
    public String status;
    public String responseCode;

    public static List<CallQueryResponseModel> getCallResponseFromCsv(List<Map<String,String>> csvData, AppConfig appConfig)
    {
        TranslateProtocols translateProtocols = new TranslateProtocols(appConfig);

        List<CallQueryResponseModel> responseObj = new ArrayList<>();
        for (int i_row=0; i_row<csvData.size(); i_row++)
        {
            Map<String,String> rowData = csvData.get(i_row);

            CallQueryResponseModel row = new CallQueryResponseModel();
            row.sn = rowData.get("SN");
            row.startTime = rowData.get("STARTTIME");
            row.callingNumber = rowData.get("Calling_Number");
            row.calledNumber = rowData.get("Called_Number");
            row.callDuration = rowData.get("CALLDURATION");
            row.status = rowData.get("STATUS");
            String raw_response_code = rowData.get("Response_Code");
            String translated_response_code = translateProtocols.translate(raw_response_code);
            row.responseCode = translated_response_code;

            responseObj.add(row);
        }
        return responseObj;
    }
}
