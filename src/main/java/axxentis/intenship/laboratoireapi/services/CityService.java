package axxentis.intenship.laboratoireapi.services;

import axxentis.intenship.laboratoireapi.entities.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    // Get all Cities
    List<City> getCities();

    // Get City by id
    Optional<City> getCity(Long id);

    //create City
    City saveCity(City city);

    //update city
    City updateCity(Long id);

    // Delete city
    void deleteCity(Long id);
}
