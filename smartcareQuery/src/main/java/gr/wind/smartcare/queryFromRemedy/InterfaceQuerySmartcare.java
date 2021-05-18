package gr.wind.smartcare.queryFromRemedy;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;


@WebService
public interface InterfaceQuerySmartcare
{
    // 1st Query
    @WebMethod
    RegisterQueryResponse makeRegisterQuery(
        @XmlElement(required=true) @WebParam(name="UserName")
        String userName,
        @XmlElement(required=true) @WebParam(name="Password")
        String password,
        @XmlElement(required=true) @WebParam(name="UserTimestamp")
        String userTimestamp,
        @XmlElement(required=true) @WebParam(name="UserCli")
        String userCli,
        @XmlElement(required=true) @WebParam(name="RequestID")
        String requestID,
        @XmlElement(required=true) @WebParam(name="RequestTimestamp")
        String requestTimestamp,
        @XmlElement(required=true) @WebParam(name="RemedyUser")
        String remedyUser,
        @XmlElement(required=true) @WebParam(name="SystemId")
        String systemId
    );

    // 2nd Query
    @WebMethod
    CallQueryResponse makeCallQuery(
        @XmlElement(required=true) @WebParam(name="UserName")
        String userName,
        @XmlElement(required=true) @WebParam(name="Password")
        String password,
        @XmlElement(required=true) @WebParam(name="UserTimestamp")
        String userTimestamp,
        @XmlElement(required=true) @WebParam(name="UserCli")
        String userCli,
        @XmlElement(required=true) @WebParam(name="RequestID")
        String requestID,
        @XmlElement(required=true) @WebParam(name="RequestTimestamp")
        String requestTimestamp,
        @XmlElement(required=true) @WebParam(name="RemedyUser")
        String remedyUser,
        @XmlElement(required=true) @WebParam(name="SystemId")
        String systemId
    );
}
