package gr.wind.smartcare.queryFromRemedy;

import java.util.List;

public class CallQueryResponse
{
    public List<CallQueryResponseModel> queryResponseData;
    public String requestId;
    public boolean errorOccurred;
    public String errorMessage;
}
