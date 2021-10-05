package axxentis.intenship.laboratoireapi.payload.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DepartmentDto {

    private Long id;
    private String name;
    private String description;
    private Date created_at;

}
