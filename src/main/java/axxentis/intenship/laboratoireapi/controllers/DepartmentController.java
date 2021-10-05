package axxentis.intenship.laboratoireapi.controllers;

import axxentis.intenship.laboratoireapi.entities.Department;
import axxentis.intenship.laboratoireapi.entities.Employee;
import axxentis.intenship.laboratoireapi.payload.dto.DepartmentDto;
import axxentis.intenship.laboratoireapi.payload.responses.ApiResponse;
import axxentis.intenship.laboratoireapi.services.DepartmentService;
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
@RequestMapping("/department")
public class DepartmentController  {

    @Autowired
    DepartmentService departmentService;

    // Get all Departments
    @GetMapping("/all")
    public ResponseEntity<?>getAllDepartments(){
        List<Department> departments = departmentService.getDepartments();
        if (!CollectionUtils.isEmpty(departments)){
            // Map the department class to DepartmentDto class
            List<DepartmentDto> allDepartmentsDto = mapList(departments, DepartmentDto.class);
            return ResponseEntity.ok(new ApiResponse(true, allDepartmentsDto, "get all successfuly!", HttpStatus.OK));
        }else {
            return ResponseEntity.ok(new ApiResponse(false, "The list is empty! ", HttpStatus.NOT_FOUND ));
        }

    }

    // Get Department by id
    @GetMapping("/{id}")
    public ResponseEntity<?>getDepartment(@PathVariable("id") final Long id){
        Optional<Department> department = departmentService.getDepartment(id);
        if (department.isPresent()){
            DepartmentDto departmentDto =  mapDepartmentToDepartmentDTOs(department);
            return ResponseEntity.ok(new ApiResponse(true, department, "Get succesfuklly", HttpStatus.OK));
        } else {
            return ResponseEntity.ok(new ApiResponse(false, "Department nout found", HttpStatus.NOT_FOUND));
        }
    }

    // Create department
    @PostMapping("/add")
    public ResponseEntity<?>createDepartment(@RequestBody Department department){
        Department saveDepartment = departmentService.saveDepartment(department);
        DepartmentDto departmentDto = mapDepartmentToDepartmentDTO(saveDepartment);
        return ResponseEntity.ok(new ApiResponse(true, departmentDto, "Save succesfully", HttpStatus.OK));
    }

    // Update departement
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable("id") final Long id, @RequestBody Department department){
        Optional<Department> d =departmentService.getDepartment(id);
        if (d.isPresent()){
            Department currentDepartment = d.get();

            String name = department.getName();
            System.out.println("Nom de departement : "+name);

            if (!name.isEmpty() || !name.isBlank()){
                currentDepartment.setName(name);
            }

            String description  = department.getDescription();
            if (!description.isEmpty() || !description.isBlank()){
                currentDepartment.setDescription(description);
            }

            DepartmentDto departmentDto = mapDepartmentToDepartmentDTO(departmentService.saveDepartment(currentDepartment));
            return ResponseEntity.ok(new ApiResponse(true, departmentDto, "Department updated succesfully", HttpStatus.OK));

        }else {
            return ResponseEntity.ok(new ApiResponse(false, "Department not found", HttpStatus.NO_CONTENT));
        }

    }

    // Delete department
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") final Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(new ApiResponse(true, "Department deleted succesfully", HttpStatus.OK));
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
     * Transform single department to  DepartmentDto
     *
     *
     * @param department
     * @return
     */
    private DepartmentDto mapDepartmentToDepartmentDTO(Department department) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(department, DepartmentDto.class);

    }
    private DepartmentDto mapDepartmentToDepartmentDTOs(Optional<Department> department) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(department, DepartmentDto.class);

    }


}
