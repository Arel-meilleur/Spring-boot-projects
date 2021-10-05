package axxentis.intenship.laboratoireapi.controllers;

import axxentis.intenship.laboratoireapi.entities.Employee;
import axxentis.intenship.laboratoireapi.payload.dto.EmployeeDto;
import axxentis.intenship.laboratoireapi.payload.responses.ApiResponse;
import axxentis.intenship.laboratoireapi.repositories.EmployeeRepository;
import axxentis.intenship.laboratoireapi.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = "/all")
    public ResponseEntity<?>getAll(){
        List<Employee> employees = employeeService.getAllEmployees();
        if (!CollectionUtils.isEmpty(employees)){
            // Map the employee class to EmployeeDto class
            List<EmployeeDto> allEmployeesDto = mapList(employees, EmployeeDto.class);
            return ResponseEntity.ok(new ApiResponse(true, employees, "Employees loaded successfully" , HttpStatus.OK));
        }else {
            return ResponseEntity.ok(new ApiResponse(false, employees, "Failed to load employees, empty List", HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping(value = "{/id}")
    public ResponseEntity<?>getEmployee(@PathVariable(value = "id") final Long id){
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        if (employee.isPresent()){
            EmployeeDto employeeDto =  mapEmployeeToEmployeeDTO(employee);
            return ResponseEntity.ok(new ApiResponse(true, employeeDto, "Employee loaded successfully", HttpStatus.OK));
        }else {
            return ResponseEntity.ok(new ApiResponse(false, "Unsuccessful attempt, employee not found", HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.addEmployee(employee);
        if (employee.getEmail().isBlank() || employee.getEmail().isEmpty()) {
            return ResponseEntity.ok(new ApiResponse(false, employee, "Please enter the employee email!", HttpStatus.PARTIAL_CONTENT));
        } else {
            EmployeeDto employeeDto = mapEmployeeToEmployeeDTO(newEmployee);
            return ResponseEntity.ok(new ApiResponse(true, employeeDto, "Employee added successfully", HttpStatus.OK));
        }

    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?>updateEmployee(@PathVariable(value = "id") final Long id, @RequestBody Employee employee){
        Employee newEmployee = employeeService.updateEmployee(id, employee);
        if (employeeService.findEmployeeById(id).isEmpty()){
            return ResponseEntity.ok(new ApiResponse(false, employee, "Employee id " + id + " not found", HttpStatus.NOT_FOUND));
        }else {
            EmployeeDto employeeDto = mapEmployeeToEmployeeDTO(newEmployee);
            return ResponseEntity.ok(new ApiResponse(true, employeeDto, "Employee updated Successfully", HttpStatus.OK));
        }

    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?>deleteEmployee(@PathVariable(value = "id") final Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(new ApiResponse(true, "Employee deleted Successfully", HttpStatus.OK));
    }

//    @GetMapping("email/{email}")
//    public List<Employee> findByEmail(@PathVariable("email") String email){
//        return employeeRepository.findByEmail(email);
//
//    }

    @GetMapping("/{firstName}/{lastName}")
    public List<Employee> findByFirstNameAndLastName(@PathVariable("firstName") String firstName,
                                                   @PathVariable("lastName") String lastName){
        return employeeRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    @GetMapping("firstName/{firstName}/{lastName}")
    public List<Employee> findByFirstNameOrderByLastName(@PathVariable("firstName") String firstName,
                                                         @PathVariable("lastName") String lastName){
        return employeeRepository.findByFirstNameOrderByLastName(firstName);

    }

    @GetMapping("gender/{gender}")
    public List<Employee> findEmployeesByGender(@PathVariable("gender") String gender){
        return employeeRepository.findEmployeesByGender(gender);

    }



    // Methods for Dto implementation

    /**
     * Transforme source List to dto list targetClass
     *
     * @param source
     * @param targetClass
     * @param <S>
     * @param <T>
     * @return
     */
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
    }

    /**
     * Transform single Employee to  EmployeeDto
     *
     *
     * @param employee
     * @return
     */
    private EmployeeDto mapEmployeeToEmployeeDTO(Employee employee) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(employee, EmployeeDto.class);

    }
    private EmployeeDto mapEmployeeToEmployeeDTO(Optional<Employee> employee) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(employee, EmployeeDto.class);

    }

}
