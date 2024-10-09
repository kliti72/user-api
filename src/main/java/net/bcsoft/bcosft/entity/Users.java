package net.bcsoft.bcosft.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "registerDate")
    private String registerDate;

    @Column(name = "lastAccess")
    private String lastAccess;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="roles_id", referencedColumnName = "id")
    private Role role;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterDate() {
        return this.registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getLastAccess() {
        return this.lastAccess;
    }

    public void setLastAccess(String lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Role getRole() { // Corretto a "Role"
        return this.role;
    }

    public void setRole(Role role) { // Corretto a "Role"
        this.role = role;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
