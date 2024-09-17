package net.bcsoft.bcosft.service;

import net.bcsoft.bcosft.dto.RoleDTO;
import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.repository.RoleRepository;
import net.bcsoft.bcosft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public RoleDTO getRoleByUserId(Long userId) {

        Users user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);

        return new RoleDTO(user.getRole().getId(), user.getRole().getName(), user.getRole().getAssignRoleBy());
    }

}