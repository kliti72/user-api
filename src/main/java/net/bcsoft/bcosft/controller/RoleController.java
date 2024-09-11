package net.bcsoft.bcosft.controller;

import net.bcsoft.bcosft.dto.RoleDTO;
import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;


    @GetMapping("/users/{userId}/role/")
    public ResponseEntity<RoleDTO> getByUserId(@PathVariable Long userId){
        //la lista è uguale al metodo di selectAll
        RoleDTO role = roleService.getRoleByUserId(userId);
        return ResponseEntity.ok(role);
    }
}
