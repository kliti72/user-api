package net.bcsoft.bcosft.entity;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="assigned_by_user_id", referencedColumnName = "id")
    private Users createBy;

}
