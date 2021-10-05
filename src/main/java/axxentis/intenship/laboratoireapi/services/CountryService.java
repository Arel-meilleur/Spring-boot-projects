package axxentis.intenship.laboratoireapi.services;

import axxentis.intenship.laboratoireapi.entities.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    // Get all Country
    List<Country> getCountries();

    // Get Country by id
    Optional<Country> getCountry(Long id);

    // Create Country
    Country saveCountry(Country country);

    // Update Coutry by id
    Country updateCountry(Long id);

    // Delete Country
    void deleteCoutry(Long id);
}
