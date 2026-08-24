package tradingWebsite.example.TradingWebsite.Exceptions;

public class EmailAlreadyExists  extends RuntimeException{
    public EmailAlreadyExists(String message){
        super(message);
    }
}
