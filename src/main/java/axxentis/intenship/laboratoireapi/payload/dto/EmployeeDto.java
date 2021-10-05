package axxentis.intenship.laboratoireapi.payload.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String created_at;
}
