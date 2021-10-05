package axxentis.intenship.laboratoireapi.payload.dto;

import lombok.Data;

@Data
public class CountryDto {

    private Long id;
    private String name;
    private String iso_code;
    private String indicative;
}
