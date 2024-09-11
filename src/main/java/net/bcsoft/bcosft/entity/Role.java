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

    public Users getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Users createBy) {
        this.createBy = createBy;
    }
}
