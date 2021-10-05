package axxentis.intenship.laboratoireapi.repositories;

import axxentis.intenship.laboratoireapi.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    JPQL Queries
//    @Query("FROM Employee WHERE email = ?1")
    Employee findByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.firstName = ?1 AND e.lastName = ?2")
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

//    Sorting
    @Query("FROM Employee WHERE firstName = ?1 ORDER BY lastName ASC")
    List<Employee> findByFirstNameOrderByLastName(String firstName);

//    Native Queries

    @Query(value =  "SELECT * FROM employee WHERE gender = :gender", nativeQuery = true)
    List<Employee> findEmployeesByGender(@Param("gender") String gender);



}
