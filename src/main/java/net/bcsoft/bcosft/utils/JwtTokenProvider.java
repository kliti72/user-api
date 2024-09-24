package net.bcsoft.bcosft.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtTokenProvider {


    @Autowired
    UserService userService;

    public static final String SECRET_KEY = "WDADKMAWHENONDJOSANOIAWNIJNWAONCIJASNFNBAOSCOSBNOANCJWANOHINAKCOJWANOJFNAWFAWKHNDKAWDJINKASDJADKWANOAKDW";

    public String generateToken(UsersDTO authentication) throws NotFoundException {

        Users user = userService.getUserByEmail(authentication.getEmail()).toEntity();
        System.out.print("UTENTE CATTURATO! LOGIN EFFETTUATO! " + user.getEmail() + user.getName());
        String username = authentication.getName();
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", authentication.getId());
        claims.put("name", authentication.getName());
        claims.put("email", authentication.getEmail());
        claims.put("password", authentication.getPassword());
        claims.put("registerDate", authentication.getRegisterDate());
        claims.put("lastAccess", authentication.getLastAccess());
        claims.put("roleId", authentication.getRoleId());

        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public static UsersDTO tokenToUserDTO(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();


        UsersDTO userDTO = new UsersDTO();
        userDTO.setId(claims.get("id", Long.class));
        userDTO.setName(claims.get("name", String.class));
        userDTO.setEmail(claims.get("email", String.class));
        userDTO.setPassword(claims.get("password", String.class));
        userDTO.setRegisterDate(claims.get("registerDate", String.class));
        userDTO.setLastAccess(claims.get("lastAccess", String.class));
        userDTO.setRoleId(claims.get("roleId", Long.class));

        return userDTO;
    }

    public boolean validateToken(String token) throws JwtException, IllegalArgumentException {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
