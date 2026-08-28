package tradingWebsite.example.TradingWebsite.Exceptions;

public class InsufficientHoldingsException extends RuntimeException {
    public InsufficientHoldingsException(String message){
        super(message);
    }
}
