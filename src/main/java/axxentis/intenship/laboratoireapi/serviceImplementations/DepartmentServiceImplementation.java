package axxentis.intenship.laboratoireapi.serviceImplementations;

import axxentis.intenship.laboratoireapi.entities.Department;
import axxentis.intenship.laboratoireapi.repositories.DepartmentRepository;
import axxentis.intenship.laboratoireapi.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImplementation implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartment(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
//
//    @Override
//    public Optional<Department> updateDepartment(Long id) {
//        Optional<Department> department = departmentRepository.findById(id);
//                .orElseThrow(()-> new InvalidConfigurationPropertyValueException("Department", id, "Ressource inexistante"));
//        String name = department.getName();
//        if (!name.isEmpty() || !name.isBlank()){
//            department.setName(name);
//        }
//
//        String description = department.getDescription();
//        if (!description.isEmpty() || !description.isBlank()){
//            department.setDescription(description);
//        }

//        return departmentRepository.save(department);
//    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
