package axxentis.intenship.laboratoireapi.serviceImplementations;

import axxentis.intenship.laboratoireapi.entities.City;
import axxentis.intenship.laboratoireapi.repositories.CityRepository;
import axxentis.intenship.laboratoireapi.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImplementation implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> getCities() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> getCity(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(()-> new InvalidConfigurationPropertyValueException("City", id, "Resource inexistante"));

        String name = city.getName();
        if (!name.isEmpty() || !name.isBlank()){
            city.setName(name);
        }

        return cityRepository.save(city);
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
