package gr.wind.smartcare.queryFromRemedy;

public class InvalidSmartcareRequestException extends Exception
{
    public InvalidSmartcareRequestException(String errorMessage)
    {
        super(errorMessage);
    }
}
