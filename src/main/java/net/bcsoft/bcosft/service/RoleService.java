package net.bcsoft.bcosft.service;

import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private UserRepository userRepository;

    public Set<Role> getRolesByUserId(Long userId) {
        Optional<Users> user = userRepository.findById(userId);
        if(!user.isPresent()){
            return null;
        }

        Set<Role> roles = user.get().getRoles();
        return roles;
    }




}