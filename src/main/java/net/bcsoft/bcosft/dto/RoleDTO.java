package net.bcsoft.bcosft.dto;

import jakarta.persistence.*;
import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;

public class RoleDTO {

    private long id;
    private String name;
    private String assignRoleBy;
    private Users createBy;


    public RoleDTO(long id, Users createBy, String assignRoleBy, String name) {
        this.id = id;
        this.createBy = createBy;
        this.assignRoleBy = assignRoleBy;
        this.name = name;
    }

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

    public Role toEntity() {
        Role role = new Role();
        role.setId(this.id);
        role.setName(this.name);
        role.setAssignRoleBy(this.assignRoleBy);
        role.setCreateBy(this.createBy);
        return role;
    }

}
