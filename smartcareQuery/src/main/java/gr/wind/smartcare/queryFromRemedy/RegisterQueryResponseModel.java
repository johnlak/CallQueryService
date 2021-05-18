package gr.wind.smartcare.queryFromRemedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Query 1
public class RegisterQueryResponseModel
{
    public String sn;
    public String startTime;
    public String endTime;
    public String serviceType;
    public String serviceStatus;
    public String sourceNeIp;
    public String msisdn;

    public static List<RegisterQueryResponseModel> getRegisterResponseFromCsv(List<Map<String,String>> csvData)
    {
        List<RegisterQueryResponseModel> responseObj = new ArrayList<>();
        for (int i_row=0; i_row<csvData.size(); i_row++)
        {
            Map<String,String> rowData = csvData.get(i_row);

            RegisterQueryResponseModel row = new RegisterQueryResponseModel();
            row.sn = rowData.get("SN");
            row.startTime = rowData.get("STARTTIME");
            row.endTime = rowData.get("ENDTIME");
            row.serviceType = rowData.get("Service_type");
            row.serviceStatus = rowData.get("Service_status");
            row.sourceNeIp = rowData.get("SOURCE_NE_IP");
            row.msisdn = rowData.get("MSISDN");
            responseObj.add(row);
        }
        return responseObj;
    }
}
