package axxentis.intenship.laboratoireapi;

import axxentis.intenship.laboratoireapi.entities.*;
import axxentis.intenship.laboratoireapi.repositories.PrivilegeRepository;
import axxentis.intenship.laboratoireapi.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
//@Slf4j
@SpringBootApplication
public class LaboratoireApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratoireApiApplication.class, args);
    }



    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner run(PrivilegeService privilegeService, EmployeeService employeeService, CountryService countryService, DepartmentService departmentService, CityService cityService, PrivilegeRepository privilegeRepository ){
//        return args -> {

//            Country country = countryService.saveCountry(new Country(null,"Cameroon","CM","237",null,null,new ArrayList<>()));
//            Department department = departmentService.saveDepartment(new Department(null,"ADS","Dep for geek",null,null,new ArrayList<>()));
//            City city = cityService.saveCity(new City(null,"Yaounde",null,null,country,new ArrayList<>()));
////            Privilege privilege =   privilegeService.addPrivilege( new Privilege(null,"SUPER ADMIN", "sqdsdq",null,null));
//            employeeService.addEmployee( new Employee(null, "meilleur2", "best","meilleur2@gmail.com","M","meilleur",null,null,null,new ArrayList<>(),department,city,new ArrayList<>()));
//
//            Privilege privilege = privilegeService.addPrivilege(new Privilege(null,"RH","Decription",null,null));
//            Privilege privilege1 = privilegeRepository.findByName("SUPER_ADMIN");
//            String privilageName = privilege1.getName();
//            log.info("Nom du privilege", privilege1);
//
//
//            employeeService.addPrivilageToEmployee("meilleur2@gmail.com",privilageName);
//
//        };
//    }



}
