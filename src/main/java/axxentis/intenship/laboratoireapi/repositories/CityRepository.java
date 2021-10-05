package axxentis.intenship.laboratoireapi.repositories;

import axxentis.intenship.laboratoireapi.entities.City;
import axxentis.intenship.laboratoireapi.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

//    En utilisant le JPQL

    //Get city by name
    @Query("SELECT c from City c where c.name=:name")
    List<City> findCitieByName(@Param("name") String name);

    // Get List of city where Country is ...  by name
    @Query("SELECT c from City c where c.name=?1 and c.country= ?2")
    List<City> findCitesNameCoutry(String name, String countryname);

    @Query("SELECT c from City c where c.name=?1 and c.country= ?2")
    List<City> findCitesCoutry(String name, String countryname);

    @Query("SELECT c from City c where c.name=:name GROUP BY c.country.id")
    List<City> findCityGroupeByCountrys(@Param("name") String name);

    @Query("SELECT ve from City v JOIN v.country ve group by ve")
    List<City> findCityGroupeByCountry(@Param("name") String name);



}
