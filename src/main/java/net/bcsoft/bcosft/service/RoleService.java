package net.bcsoft.bcosft.service;

import net.bcsoft.bcosft.dto.RoleDTO;
import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.repository.RoleRepository;
import net.bcsoft.bcosft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public RoleDTO getRoleByUserId(Long userId) throws NotFoundException {

        Users user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);

        if(user.getRole() == null) {
            throw new NullPointerException("Errore nessun ruolo trovato per questo utente");
        }

        Role role = roleRepository.findById(user.getRole().getId())
                .orElseThrow(NotFoundException::new);

        return new RoleDTO(role.getId(), role.getName(), role.getAssignRoleBy());
    }

    public List<RoleDTO> getRoles() throws NotFoundException {

        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> rolesDTO = new ArrayList<>();

        for(Role role : roles){
            rolesDTO.add(new RoleDTO(role.getId(), role.getName(), role.getAssignRoleBy()));
        }

        return rolesDTO;
    }

    public RoleDTO insert(RoleDTO roleDTO) {

        Role role = roleRepository.save(roleDTO.toEntity());

        return new RoleDTO(role.getId(), role.getName(), role.getAssignRoleBy());

    }

    public RoleDTO update(RoleDTO roleDTO, Long roleOverWriteId) {

        Role role = roleRepository.getReferenceById(roleOverWriteId);

        role.setName(roleDTO.getName());
        role.setAssignRoleBy(roleDTO.getAssignRoleBy());


        Role updateRole = roleRepository.save(role);

        return new RoleDTO(updateRole.getId(), updateRole.getName(), updateRole.getAssignRoleBy());
    }

    public ResponseEntity<HttpStatus> delete(Long roleId) throws NotFoundException {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(NotFoundException::new);

        roleRepository.delete(role);

        return ResponseEntity.ok(HttpStatus.OK);
    }

}