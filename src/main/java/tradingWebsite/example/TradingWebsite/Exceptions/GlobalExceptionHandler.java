package tradingWebsite.example.TradingWebsite.Exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<Map<String,Object>> handleEmailExists(EmailAlreadyExists ex){
        return buildResponse(HttpStatus.CONFLICT,ex.getMessage());
    }   


    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<Map<String,Object>> handleInvalidCredentials(InvalidCredentials ex){
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

 @ExceptionHandler(InsufficientFundException.class)
    public ResponseEntity<Map<String,Object>> handleInsufficientFunds(InsufficientFundException ex){
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
     @ExceptionHandler(InsufficientHoldingsException.class)
    public ResponseEntity<Map<String,Object>> handleInsufficientHoldings(InsufficientHoldingsException ex){
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }



  @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Validation failed");
        body.put("fields", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

      @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
    }


    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", message);
        return ResponseEntity.status(status).body(body);
    }

    
}
