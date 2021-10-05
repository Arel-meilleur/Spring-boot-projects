package axxentis.intenship.laboratoireapi.payload.dto;

import lombok.Data;

@Data
public class PrivilegeDto {
    private Long id;
    private String name;
    private String description;
    private String created_at;
}
