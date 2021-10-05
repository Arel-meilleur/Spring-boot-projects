package axxentis.intenship.laboratoireapi.serviceImplementations;

import axxentis.intenship.laboratoireapi.entities.Privilege;
import axxentis.intenship.laboratoireapi.repositories.PrivilegeRepository;
import axxentis.intenship.laboratoireapi.services.PrivilegeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class PrivilegeServiceImplementation implements PrivilegeService {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Override
    public List<Privilege> getAllPrivileges() {
        return privilegeRepository.findAll();
    }

    @Override
    public Optional<Privilege> findPrivilegeById(Long id) {
        return privilegeRepository.findById(id);
    }


    @Override
    public Privilege addPrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege);

    }

    @Override
    public Privilege updatePrivilege(Long id, Privilege privilege) {
        Optional<Privilege> privilegeToUpdate = findPrivilegeById(id);
        privilegeToUpdate.get().setDescription(privilege.getDescription());
        privilegeToUpdate.get().setName(privilege.getName());
        return privilegeRepository.save(privilegeToUpdate.get());

    }

    @Override
    public void deletePrivilege(Long id) {
        privilegeRepository.deleteById(id);
    }
}
