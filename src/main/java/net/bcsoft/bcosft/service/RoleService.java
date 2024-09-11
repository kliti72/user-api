package net.bcsoft.bcosft.service;

import net.bcsoft.bcosft.dto.RoleDTO;
import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.repository.RoleRepository;
import net.bcsoft.bcosft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public RoleDTO getRoleByUserId(Long userId) {

        Optional<Users> user = userRepository.findById(userId);
        Role role = user.get().getRole();

        return new RoleDTO(role.getId(), role.getName(), role.getAssignRoleBy());
    }





}