package axxentis.intenship.laboratoireapi.exceptions;

import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice  // This anatation is about managin exception globaly
public class GlobalExceptionHandler {

    // Manage APIException (Runtime exception)
    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleAPIException(APIException e , WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false), 404, "NOK");
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Mnaipulate InvalidConfigurationPropertyValueException
    @ExceptionHandler(InvalidConfigurationPropertyValueException.class)
    public ResponseEntity<?> handleInvalidConfigurationPropertyValueException(InvalidConfigurationPropertyValueException e , WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false) , 404, "NOK" );
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Manage Global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e , WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false), 500, "NOK");
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
