package net.bcsoft.bcosft.controller;

import net.bcsoft.bcosft.config.CustomUserDetails;
import net.bcsoft.bcosft.dto.JwtResponseDTO;
import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.exception.PasswordNotCorrectException;
import net.bcsoft.bcosft.service.UserService;
import org.apache.catalina.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    public AuthenticationController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login/")
    public ResponseEntity<Users> authenticateUser(@RequestBody UsersDTO userDTO) {

        Users user = userService.getUserByEmail(userDTO.getEmail());

        if(userDTO.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/register/")
    public ResponseEntity<String> register(@RequestBody UsersDTO user) {
        UsersDTO userDTO  = userService.insert(user);
        String token = jwtTokenProvider.generateToken(userDTO.toEntity());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/validate/")
    public ResponseEntity<Boolean> tokenToUserDTO(@RequestHeader("Authorization") String token) {
        try {
            jwtTokenProvider.validateToken(token);
            System.out.print("TOKEN DECRIPT" + jwtTokenProvider.tokenToUserDTO(token).getName());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("/current-user/")
    public UsersDTO getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UsersDTO userDTO = new UsersDTO();

        userDTO.setSurname(userDetails.getUsername());
        userDTO.setEmail(userDetails.getEmail());
        return userDTO;
    }


}
