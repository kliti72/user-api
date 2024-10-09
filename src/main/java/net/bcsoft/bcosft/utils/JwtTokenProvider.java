package net.bcsoft.bcosft.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import net.bcsoft.bcosft.config.CustomUserDetails;
import net.bcsoft.bcosft.dto.RoleDTO;
import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.service.RoleService;
import net.bcsoft.bcosft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtTokenProvider {

    private static final String SECRET_KEY = "WDADKMAWHENONDJOSANOIAWNIJNWAONCIJASNFNBAOSCOSBNOANCJWANOHINAKCOJWANOJFNAWFAWKHNDKAWDJINKASDJADKWANOAKDW";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    // Generazione del token JWT con i ruoli
    public String generateToken(UsersDTO authentication) {
        Users user = userService.getUserByEmail(authentication.getEmail()).toEntity();
        String username = authentication.getName();
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", authentication.getId());
        claims.put("name", authentication.getName());
        claims.put("email", authentication.getEmail());
        claims.put("registerDate", authentication.getRegisterDate());
        claims.put("lastAccess", authentication.getLastAccess());
        claims.put("roleId", authentication.getRoleId());

        // Estrai i ruoli e metti nel token come mappa
        RoleDTO roleDTO = roleService.getRoleByUserId(authentication.getId());
        Map<String, String> roleMap = new LinkedHashMap<>();
        roleMap.put("authority", roleDTO.getName());
        claims.put("roles", roleMap);

        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // Conversione del token JWT in un oggetto UsersDTO
    public UsersDTO tokenToUserDTO(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        UsersDTO userDTO = new UsersDTO();
        userDTO.setId(claims.get("id", Long.class));
        userDTO.setName(claims.get("name", String.class));
        userDTO.setEmail(claims.get("email", String.class));
        userDTO.setRegisterDate(claims.get("registerDate", String.class));
        userDTO.setLastAccess(claims.get("lastAccess", String.class));
        userDTO.setRoleId(claims.get("roleId", Long.class));

        return userDTO;
    }

    // Validazione del token JWT
    public boolean validateToken(String token) {
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

    // Estrazione dei dettagli utente dal token JWT
    public UserDetails getUserDetailsFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        UsersDTO userDTO = new UsersDTO();
        userDTO.setId(claims.get("id", Long.class));
        userDTO.setName(claims.get("name", String.class));
        userDTO.setEmail(claims.get("email", String.class));
        userDTO.setRegisterDate(claims.get("registerDate", String.class));
        userDTO.setLastAccess(claims.get("lastAccess", String.class));
        userDTO.setRoleId(claims.get("roleId", Long.class));

        // Gestisci la mappa dei ruoli
        Map<String, String> roleMap = claims.get("roles", Map.class);
        String role = roleMap.get("authority");

        // Crea un'autorità usando il ruolo
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

        return new CustomUserDetails(userDTO.getName(), userDTO.getEmail(), "", authorities);
    }
}
