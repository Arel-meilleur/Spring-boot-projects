package axxentis.intenship.laboratoireapi.serviceImplementations;

import axxentis.intenship.laboratoireapi.entities.Country;
import axxentis.intenship.laboratoireapi.entities.Employee;
import axxentis.intenship.laboratoireapi.repositories.CountryRepository;
import axxentis.intenship.laboratoireapi.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyNameException;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImplementation implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> getCountry(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(()-> new InvalidConfigurationPropertyValueException("Country", id, "Cette ressource n'existe pas"));
        String name = country.getName();
        if (!name.isEmpty() || !name.isBlank()){
            country.setName(name);
        }

        String iso_code = country.getIso_code();
        if (!iso_code.isEmpty() || !iso_code.isBlank()){
            country.setIso_code(iso_code);
        }

        String indicative = country.getIndicative();
        if (!indicative.isEmpty() || !indicative.isBlank()){
            country.setIndicative(indicative);
        }

        return countryRepository.save(country);
    }

    @Override
    public void deleteCoutry(Long id) {
        countryRepository.deleteById(id);
    }
}
