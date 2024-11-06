package net.bcsoft.bcosft.controller;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.entity.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long JWT_EXPIRATION;

    public String generateToken(Users user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        claims.put("registerDate", user.getRegisterDate());
        claims.put("lastAccess", user.getLastAccess());

        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public static String cleanToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return authorizationHeader;
    }

    public UsersDTO tokenToUserDTO(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
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

        } catch (ExpiredJwtException e) {
            System.out.println("Il token è scaduto.");
        } catch (DecodingException e) {
            System.out.println("Errore di decodifica del token.");
        } catch (JwtException e) {
            System.out.println("Errore generico nel parsing del token.");
        }

        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Token non valido: " + e.getMessage());
            return false;
        }
    }
}