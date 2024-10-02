package net.bcsoft.bcosft.controller;

import net.bcsoft.bcosft.config.CustomUserDetails;
import net.bcsoft.bcosft.dto.JwtResponseDTO;
import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.service.UserService;
import net.bcsoft.bcosft.utils.JwtTokenProvider;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    public AuthenticationController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login/")
    public ResponseEntity<JwtResponseDTO> authenticateUser(@RequestBody UsersDTO userDTO) throws ChangeSetPersister.NotFoundException {
        String jwt = jwtTokenProvider.generateToken(userDTO);
        return ResponseEntity.ok(new JwtResponseDTO(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody UsersDTO user) {
        userService.insert(user);

        return ResponseEntity.ok(true);
    }

    @GetMapping("/validate/")
    public ResponseEntity<Boolean> tokenToUserDTO(@RequestHeader("Authorization") String token) {
        try {
            jwtTokenProvider.validateToken(token);
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
