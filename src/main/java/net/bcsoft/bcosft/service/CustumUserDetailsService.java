package net.bcsoft.bcosft.service;

import jdk.jfr.Description;
import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustumUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    @Description("Costruisce l'utente data un'email")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users user = null;

        user = userService.getUserByEmail(email).toEntity();

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword() , getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        return List.of(new SimpleGrantedAuthority(user.getRole().getName()));
    }


}
