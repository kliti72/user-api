package net.bcsoft.bcosft.service;

import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Users> getUserById(Long userId) {
        Optional<Users> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Set<Role>> getRolesByUserId(Long userId) {
        Optional<Users> user = userRepository.findById(userId);

        if (user.isPresent()) {
            Set<Role> roles = user.get().getRoles();
            return new ResponseEntity<>(roles, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
