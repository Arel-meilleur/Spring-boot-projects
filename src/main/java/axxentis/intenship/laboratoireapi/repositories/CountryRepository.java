package axxentis.intenship.laboratoireapi.repositories;

import axxentis.intenship.laboratoireapi.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    // EN utilisant le JPQL

    // Get coutry by name
    @Query("SELECT c from Country c where c.name=:name")
    List<Country> findByName(@Param("name") String name);

    //EN utilisant le nativeQuery
    @Query(value = "SELECT * from Country c where c.name=:name", nativeQuery = true)
    List<Country> findByNameNative(@Param("name") String name);



}
