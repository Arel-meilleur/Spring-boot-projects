package axxentis.intenship.laboratoireapi.services;

import axxentis.intenship.laboratoireapi.entities.Privilege;

import java.util.List;
import java.util.Optional;

public interface PrivilegeService {
    List<Privilege> getAllPrivileges();

    Optional<Privilege> findPrivilegeById(Long id);

    Privilege addPrivilege(Privilege privilege);

    Privilege updatePrivilege(Long id, Privilege privilege);

    void deletePrivilege(Long id);

}
