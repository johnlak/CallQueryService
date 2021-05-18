package gr.wind.smartcare.queryFromRemedy;

import java.util.List;

public class RegisterQueryResponse
{
    public List<RegisterQueryResponseModel> queryResponseData;
    public String requestId;
    public boolean errorOccurred;
    public String errorMessage;
}
