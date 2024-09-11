package net.bcsoft.bcosft.entity;// Role.java

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "assignRoleBy")
    private String assignRoleBy;


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

    public String getAssignRoleBy() {
        return assignRoleBy;
    }

    public void setAssignRoleBy(String assignRoleBy) {
        this.assignRoleBy = assignRoleBy;
    }

    }
