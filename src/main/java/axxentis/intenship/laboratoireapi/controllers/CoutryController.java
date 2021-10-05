package axxentis.intenship.laboratoireapi.controllers;

import axxentis.intenship.laboratoireapi.entities.Country;
import axxentis.intenship.laboratoireapi.payload.dto.CountryDto;
import axxentis.intenship.laboratoireapi.payload.responses.ApiResponse;
import axxentis.intenship.laboratoireapi.repositories.CountryRepository;
import axxentis.intenship.laboratoireapi.services.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/country")
public class CoutryController {

    @Autowired
    CountryService countryService;

    @Autowired
    CountryRepository countryRepository;

    //Get all countries
    @GetMapping("all")
    public ResponseEntity<?> getAllCountry(){
        List<Country> countries = countryService.getCountries();
        if (!CollectionUtils.isEmpty(countries)){
            List<CountryDto> countryDtos = mapList(countries, CountryDto.class);
            return ResponseEntity.ok(new ApiResponse(true, countryDtos, "Get country succsesfully", HttpStatus.OK));
        } else {
            return ResponseEntity.ok(new ApiResponse(false, "The list is empty", HttpStatus.NO_CONTENT));
        }
    }

    //Get country by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getContryByid(@PathVariable("id") final Long id){
        Optional<Country> country = countryService.getCountry(id);
        if (country.isPresent()){
            return ResponseEntity.ok(new ApiResponse(true, country, "Get country succesfully", HttpStatus.OK));
        } else {
            return ResponseEntity.ok(new ApiResponse(true, "The list is empty", HttpStatus.NO_CONTENT));
        }
    }

    // Create department
    @PostMapping("/add")
    public ResponseEntity<?>createCountry(@RequestBody Country country){
        Country saveCountry = countryService.saveCountry(country);
        CountryDto countryDto = mapCountryCountryDTO(saveCountry);
        return ResponseEntity.ok(new ApiResponse(true, countryDto, "Save succesfully", HttpStatus.OK));
    }

    // Update Country
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDCountry(@PathVariable("id") final Long id, @RequestBody Country country){
        Optional<Country> c =countryService.getCountry(id);
        if (c.isPresent()){
            Country currentCountry = c.get();

            String name = country.getName();

            if (!name.isEmpty() || !name.isBlank()){
                currentCountry.setName(name);
            }

            String iso_code  = country.getIso_code();
            if (!iso_code.isEmpty() || !iso_code.isBlank()){
                currentCountry.setIso_code(iso_code);
            }
            String indicative  = country.getIndicative();
            if (!indicative.isEmpty() || !indicative.isBlank()){
                currentCountry.setIndicative(indicative);
            }

            CountryDto countryDto = mapCountryCountryDTO(countryService.saveCountry(currentCountry));
            return ResponseEntity.ok(new ApiResponse(true, countryDto, "Country updated succesfully", HttpStatus.OK));

        }else {
            return ResponseEntity.ok(new ApiResponse(false, "Country not found", HttpStatus.NO_CONTENT));
        }
    }

    // Delete Country by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable("id") final Long id){
        countryService.deleteCoutry(id);
        return ResponseEntity.ok(new ApiResponse(true, "Country deleted succesfully", HttpStatus.OK));
    }

    // Get Country By name
    @GetMapping("/coun/{name}")
    public ResponseEntity<ApiResponse> findByNamee(@PathVariable("name") String name){
        List<Country> countries = countryRepository.findByName(name);
        if (!CollectionUtils.isEmpty(countries)){
            return ResponseEntity.ok(new ApiResponse(true, countries, "Coutry get succesfully", HttpStatus.OK));
        }else {
            return ResponseEntity.ok(new ApiResponse(false, "Faild to get country", HttpStatus.NO_CONTENT));
        }
    }


    // Methods for Dto implementation

    /**
     * Transforme source List to dto list targetClass
     *
     * @param source
     * @param targetClass
     * @param <S>
     * @param <T>
     * @return
     */
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }

    /**
     * Transform single department to  DepartmentDto
     *
     *
     * @param country
     * @return
     */
    private CountryDto mapCountryCountryDTO(Country country) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(country, CountryDto.class);

    }

}
