package net.bcsoft.bcosft.controller;


import net.bcsoft.bcosft.dto.JwtResponseDTO;
import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.repository.UserRepository;
import net.bcsoft.bcosft.utils.JwtTokenProvider;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserLoginController {

    private final JwtTokenProvider jwtTokenProvider;
    public UserLoginController(JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/auth/")
    public ResponseEntity<JwtResponseDTO> authenticateUser(@RequestBody UsersDTO userDTO) throws ChangeSetPersister.NotFoundException {

        String jwt = jwtTokenProvider.generateToken(userDTO);

        return ResponseEntity.ok(new JwtResponseDTO(jwt));
    }

    @GetMapping("/auth/validate/")
    public ResponseEntity<Users> tokenToUserDTO(@RequestHeader("Authorization") String token) {
        Users users;

        if(jwtTokenProvider.validateToken(token)){
            users = jwtTokenProvider.tokenToUserDTO(token).toEntity();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        return ResponseEntity.ok(users);
    }

}
