package axxentis.intenship.laboratoireapi.serviceImplementations;

import axxentis.intenship.laboratoireapi.entities.Employee;
import axxentis.intenship.laboratoireapi.entities.Privilege;
import axxentis.intenship.laboratoireapi.repositories.EmployeeRepository;
import axxentis.intenship.laboratoireapi.repositories.PrivilegeRepository;
import axxentis.intenship.laboratoireapi.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImplementation implements EmployeeService, UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;
    PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null){
            throw new UsernameNotFoundException("User not found in the database");
        }else {
            log.info("User not foud un the dadabase");
        }
        Collection<SimpleGrantedAuthority> autorities = new ArrayList<>();
        employee.getPrivileges().forEach(privilege -> {
            autorities.add(new SimpleGrantedAuthority(privilege.getName()));
        });
        return new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassword(),autorities);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        log.info("Savig new employee {} to the daabase");
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    @Override
    public void addPrivilageToEmployee(String email, String privilegeName) {
        Employee employee = employeeRepository.findByEmail(email);
        Privilege privilege = privilegeRepository.findByName(privilegeName);
        employee.getPrivileges().add(privilege);

    }

    @Override
    public Employee getEmployee(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> employeeToUpdate = findEmployeeById(id);
        employeeToUpdate.get().setEmail(employee.getEmail());
        return employeeRepository.save(employeeToUpdate.get());
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


}
