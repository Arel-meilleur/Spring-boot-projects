package axxentis.intenship.laboratoireapi.services;

import axxentis.intenship.laboratoireapi.entities.Employee;
import axxentis.intenship.laboratoireapi.entities.Privilege;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Optional<Employee> findEmployeeById(Long id);

    Employee addEmployee(Employee employee);

    void addPrivilageToEmployee(String email, String privilegeName);

    Employee getEmployee(String email);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);


}
