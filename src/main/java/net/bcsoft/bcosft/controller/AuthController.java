package net.bcsoft.bcosft.controller;

import jakarta.servlet.http.HttpServletRequest;
import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.service.JwtTokenService;
import net.bcsoft.bcosft.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenService jwtTokenService;
    private final UserService userService;

    public AuthController(JwtTokenService jwtTokenService, UserService userService) {
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
    }

    @PostMapping("/login/")
    public ResponseEntity<String> login(@RequestBody UsersDTO userDTO) {
        return userService.login(userDTO)
                .map(userId -> {
                    String token = jwtTokenService.generateToken(userId, "1");
                    return new ResponseEntity<>("Bearer " + token, HttpStatus.OK);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    @PostMapping("/register/")
    public ResponseEntity<String> register(@RequestBody UsersDTO userDTO) {
        return userService.register(userDTO)
                .map(userId -> {
                    String token = jwtTokenService.generateToken(userId, "1");
                    return new ResponseEntity<>("Bearer " + token, HttpStatus.OK);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    @PostMapping("/current-user/")
    public ResponseEntity<UsersDTO> getCurrunUser(HttpServletRequest httpServletRequest) {
        Long userId = jwtTokenService.getUserIdFromToken(httpServletRequest);
        UsersDTO user = userService.selectById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}