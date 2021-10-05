package axxentis.intenship.laboratoireapi.repositories;

import axxentis.intenship.laboratoireapi.entities.Employee;
import axxentis.intenship.laboratoireapi.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    //    JPQL Queries
    @Query("FROM Image WHERE url = ?1")
    List<Image> findByUrl(String url);

    //    Sorting
    @Query("FROM Image WHERE url = ?1 ORDER BY id ASC")
    List<Image> findByUrlOrderById(String Url);

}
