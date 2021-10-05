package axxentis.intenship.laboratoireapi.payload.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private List data;
    private Object value;
    private final Boolean success;
    private final String timestamp;
    private final String cause;
    private final String path;
    private String message;
    private HttpStatus httpStatus;

    public ApiResponse(Boolean success, Object value, String cause, String path, String message) {
        this.timestamp = Instant.now().toString();
        this.value = value;
        this.success = success;
        this.cause = cause;
        this.path = path;
        this.message = message;
    }

    /**
     * @param success
     * @param message
     */
    public ApiResponse(Boolean success, String message, HttpStatus httpStatus) {
        this.timestamp = Instant.now().toString();
        this.value = null;
        this.success = success;
        this.cause = null;
        this.path = null;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ApiResponse(Boolean success, Object value, String message, HttpStatus httpStatus) {
        this.timestamp = Instant.now().toString();
        this.value = value;
        this.success = success;
        this.cause = null;
        this.path = null;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ApiResponse(Boolean success, List<?> data, String message, HttpStatus httpStatus) {
        this.timestamp = Instant.now().toString();
        this.data = data;
        this.success = success;
        this.cause = null;
        this.path = null;
        this.message = message;
        this.httpStatus = httpStatus;
    }


    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }

    public String getCause() {
        return cause;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
