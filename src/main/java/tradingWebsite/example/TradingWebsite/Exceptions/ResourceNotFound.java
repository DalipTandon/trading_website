package tradingWebsite.example.TradingWebsite.Exceptions;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String message){
        super(message);
    }
}
