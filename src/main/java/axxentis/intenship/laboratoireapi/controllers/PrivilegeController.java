package axxentis.intenship.laboratoireapi.controllers;

import axxentis.intenship.laboratoireapi.entities.Image;
import axxentis.intenship.laboratoireapi.entities.Privilege;
import axxentis.intenship.laboratoireapi.payload.dto.PrivilegeDto;
import axxentis.intenship.laboratoireapi.payload.responses.ApiResponse;
import axxentis.intenship.laboratoireapi.repositories.PrivilegeRepository;
import axxentis.intenship.laboratoireapi.services.EmployeeService;
import axxentis.intenship.laboratoireapi.services.PrivilegeService;
import lombok.Data;
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
@RequestMapping("/privilege")
public class PrivilegeController {

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/all")
    public ResponseEntity<?>getAll(){
        List<Privilege> privileges = privilegeService.getAllPrivileges();
        if (!CollectionUtils.isEmpty(privileges)){
            // Map the employee class to ImageDto class
            List<PrivilegeDto> allPrivilegesDto = mapList(privileges, PrivilegeDto.class);
            return ResponseEntity.ok(new ApiResponse(true, allPrivilegesDto, "Privileges loaded successfully" , HttpStatus.OK));
        }else {
            return ResponseEntity.ok(new ApiResponse(false, privileges, "Failed to load privileges, empty List", HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping(value = "{/id}")
    public ResponseEntity<?>getPrivilege(@PathVariable(value = "id") final Long id){
        Optional<Privilege> privilege = privilegeService.findPrivilegeById(id);
        if (privilege.isPresent()){
            PrivilegeDto privilegeDto = mapPrivilegeToPrivilegeDTO(privilege);
            return ResponseEntity.ok(new ApiResponse(true, privilegeDto, "Privilege loaded successfully", HttpStatus.OK));
        }else {
            return ResponseEntity.ok(new ApiResponse(false, privilege, "Unsuccessful attempt, privilege not found", HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addPrivilege(@RequestBody Privilege privilege) {
        Privilege newPrivilege = privilegeService.addPrivilege(privilege);
        if (privilege.getDescription().isBlank() || privilege.getDescription().isEmpty()) {
            return ResponseEntity.ok(new ApiResponse(false, privilege, "Please enter the privilege description!", HttpStatus.PARTIAL_CONTENT));
        }else {
            PrivilegeDto privilegeDto = mapPrivilegeToPrivilegeDTO(newPrivilege);
            return ResponseEntity.ok(new ApiResponse(true, privilegeDto, "Privilege added successfully", HttpStatus.OK));
        }

    }
    // Add role privilage to employee
    @PostMapping("/role/addtoemployee")
    public ResponseEntity<?> addPrivilageToEmployee(@RequestBody PrivilegeForm form){
        employeeService.addPrivilageToEmployee(form.getEmail(), form.getPrivilegeName());
        return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?>updatePrivilege(@PathVariable(value = "id") final Long id, @RequestBody Privilege privilege){
        Privilege newPrivilege = privilegeService.updatePrivilege(id, privilege);
        if (privilegeService.findPrivilegeById(id).isEmpty()){
            return ResponseEntity.ok(new ApiResponse(false, privilege, "Privilege id " + id + " not found", HttpStatus.NOT_FOUND));
        }else {
            PrivilegeDto privilegeDto = mapPrivilegeToPrivilegeDTO(newPrivilege);
            return ResponseEntity.ok(new ApiResponse(true, privilegeDto, "Privilege updated Successfully", HttpStatus.OK));
        }

    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?>deletePrivilege(@PathVariable(value = "id") final Long id){
        privilegeService.deletePrivilege(id);
        return ResponseEntity.ok(new ApiResponse(true, "Privilege deleted Successfully", HttpStatus.OK));
    }

//    @GetMapping("name/{name}")
//    public List<Privilege> findByName(@PathVariable("name") String name){
//        return privilegeRepository.findByName(name);
//
//    }

    @GetMapping("name/{name}/{description}")
    public List<Privilege> findByNameAndDescription(@PathVariable("name") String name,
                                          @PathVariable("description") String description){
        return privilegeRepository.findByNameAndDescription(name, description);

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
     * Transform single Privilege to  PrivilegeDto
     *
     *
     * @param privilege
     * @return
     */
    private PrivilegeDto mapPrivilegeToPrivilegeDTO(Privilege privilege) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(privilege, PrivilegeDto.class);

    }
    private PrivilegeDto mapPrivilegeToPrivilegeDTO(Optional<Privilege> privilege) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(privilege, PrivilegeDto.class);

    }
}

// Class to add privilage
@Data
class PrivilegeForm {
    private String email;
    private String privilegeName;
}


