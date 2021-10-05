package axxentis.intenship.laboratoireapi.exceptions;

public class APIException extends RuntimeException {

    public static final Long serialVertionUDI = 1L;

    public APIException(String message) {
        super(message);
    }
}
