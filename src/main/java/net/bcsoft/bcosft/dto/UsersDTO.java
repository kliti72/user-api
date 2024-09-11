package net.bcsoft.bcosft.dto;

import jakarta.persistence.*;
import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;

import java.util.Set;

public class UsersDTO {

    private long id;
    private String name;
    private String surname;
    private String password;
    private String registerDate;
    private String lastAccess;
    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(String lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    private Users toEntity () {
        Users user = new Users();

        user.setId(this.id);
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setRoles(this.roles);
        user.setPassword(this.password);
        user.setLastAccess(this.lastAccess);
        user.setRegisterDate(this.registerDate);
        return user;
    }
}
