package net.bcsoft.bcosft.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.bcsoft.bcosft.dto.UsersDTO;
import org.springframework.stereotype.Service;

@Service
public class jwtManager {

    public static final String SECRET_KEY = "L93920KWJmmn299KWM";

    public static String generateToken(UsersDTO userDTO) {
        Claims claims = Jwts.claims().setSubject(userDTO.getName());
        claims.put("name", userDTO.getName());
        claims.put("email", userDTO.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static UsersDTO tokenToUserDTO(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

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

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

}
