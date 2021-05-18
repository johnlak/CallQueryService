package gr.wind.smartcare.queryFromRemedy;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

@WebService(endpointInterface = "gr.wind.smartcare.queryFromRemedy.InterfaceQuerySmartcare")
public class QuerySmartcare
{
    // 1st Query
    @WebMethod
    public RegisterQueryResponse makeRegisterQuery(
            @WebParam(name="UserName") @XmlElement(required=true) String userName,
            @WebParam(name="Password") @XmlElement(required=true) String password,
            @WebParam(name="UserTimestamp") @XmlElement(required=true) String userTimestamp,
            @WebParam(name="UserCli") @XmlElement(required=true) String userCli,
            @WebParam(name="RequestID") @XmlElement(required=true) String requestID,
            @WebParam(name="RequestTimestamp") @XmlElement(required=true) String requestTimestamp,
            @WebParam(name="RemedyUser") @XmlElement(required=true) String remedyUser,
            @WebParam(name="SystemId") @XmlElement(required=true) String systemId
    )
    {
        RegisterQuery registerQuery = new RegisterQuery();
        return registerQuery.makeRegisterQuery(userName, password, userTimestamp, userCli, requestID, requestTimestamp, remedyUser, systemId);
    }

    // 2nd Query
    @WebMethod
    public CallQueryResponse makeCallQuery(
            @WebParam(name="UserName") @XmlElement(required=true) String userName,
            @WebParam(name="Password") @XmlElement(required=true) String password,
            @WebParam(name="UserTimestamp") @XmlElement(required=true) String userTimestamp,
            @WebParam(name="UserCli") @XmlElement(required=true) String userCli,
            @WebParam(name="RequestID") @XmlElement(required=true) String requestID,
            @WebParam(name="RequestTimestamp") @XmlElement(required=true) String requestTimestamp,
            @WebParam(name="RemedyUser") @XmlElement(required=true) String remedyUser,
            @WebParam(name="SystemId") @XmlElement(required=true) String systemId
    )
    {
        CallQuery callQuery = new CallQuery();
        return callQuery.makeCallQuery(userName, password, userTimestamp, userCli, requestID, requestTimestamp, remedyUser, systemId);
    }
}
