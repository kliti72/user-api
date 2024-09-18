package net.bcsoft.bcosft.controller;

import jdk.jfr.Description;
import net.bcsoft.bcosft.dto.RoleDTO;
import net.bcsoft.bcosft.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;


@RestController

public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/role/user/{userId}/")
    @Description("Recovery the role with User id")
    public ResponseEntity<RoleDTO> getByUserId(@PathVariable Long userId){

        RoleDTO role;

        try {
            role = roleService.getRoleByUserId(userId);
        } catch(NotFoundException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(role);
    }

    @PostMapping("/role/")
    @Description("Create new role for all user")
    public ResponseEntity<RoleDTO> insert(@RequestBody RoleDTO roleDTO) {

        RoleDTO role = roleService.insert(roleDTO);

        return ResponseEntity.ok(role);

    }

    @PutMapping("/role/{roleId}/")
    @Description("Update specific role")
    public ResponseEntity<RoleDTO> put(@RequestBody RoleDTO roleDTO, @PathVariable Long roleId) {

        RoleDTO role = roleService.update(roleDTO, roleId);

        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/role/{roleId}/")
    @Description("Delete the role with role id")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long roleId) {

        try {
            roleService.delete(roleId);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
