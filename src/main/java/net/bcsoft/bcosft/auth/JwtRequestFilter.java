package net.bcsoft.bcosft.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.bcsoft.bcosft.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;

    @Autowired
    public JwtRequestFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().equals("/auth/register/")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String token = request.getHeader("Authorization");

            if (token != null && token.startsWith("Bearer ")) {
                // Valida il token JWT
                String tokenWithoutBearer = token.substring(7);
                Authentication authentication = jwtTokenService.getAuthentication(tokenWithoutBearer);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterAfter(
                this,
                UsernamePasswordAuthenticationFilter.class
        );
        http.csrf(AbstractHttpConfigurer::disable);
        http.csrf().ignoringRequestMatchers("/auth/login/", "/auth/register/");
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login/", "/auth/register/").permitAll()

                .anyRequest().authenticated());


        return http.build();
    }
}