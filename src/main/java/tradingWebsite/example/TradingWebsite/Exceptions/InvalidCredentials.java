package tradingWebsite.example.TradingWebsite.Exceptions;

public class InvalidCredentials extends RuntimeException {
    public InvalidCredentials(String message){
        super(message);
    }
}
