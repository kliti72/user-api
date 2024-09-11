package net.bcsoft.bcosft.service;

import net.bcsoft.bcosft.dto.RoleDTO;
import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.repository.RoleRepository;
import net.bcsoft.bcosft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public RoleDTO getRoleByUserId(Long userId) {
        Optional<Users> user = userRepository.findById(userId);

        Role role = userRepository.getRole(user);

        if(!user.isPresent()){
            return null;
        }

        return new RoleDTO(role.getId(), role.getCreateBy(), role.getAssignRoleBy(), role.getName());

    }





}