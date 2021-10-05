package axxentis.intenship.laboratoireapi.repositories;

import axxentis.intenship.laboratoireapi.entities.Employee;
import axxentis.intenship.laboratoireapi.entities.Image;
import axxentis.intenship.laboratoireapi.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

//    JPQL Queries
//    @Query("from Privilege where name = ?1")
    Privilege findByName(String name);

    @Query("SELECT e from Privilege e where e.name = ?1 AND e.description = ?2")
    List<Privilege> findByNameAndDescription(String name, String description);

}
