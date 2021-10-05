package axxentis.intenship.laboratoireapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private Date date;
    private String message;
    private String details;
    private Integer statusCode;
    private String status;

}
