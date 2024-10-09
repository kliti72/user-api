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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
public class RoleController {

    RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/role/user/{userId}/")
    @Description("Recovery the role with User id")
    public ResponseEntity<RoleDTO> getByUserId(@PathVariable Long userId){
        RoleDTO role = roleService.getRoleByUserId(userId);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/roles/")
    @Description("Recovery the role with User id")
    public ResponseEntity<List<RoleDTO>> getRoles(){
        List<RoleDTO> role= roleService.getRoles();
        return ResponseEntity.ok(role);
    }

    @PostMapping("/role/")
    @Description("Create new role for all user")
    public ResponseEntity<RoleDTO> insert(@RequestBody RoleDTO roleDTO) throws URISyntaxException {
        RoleDTO roleDTO1 = roleService.insert(roleDTO);

        return ResponseEntity.created(new URI("/roles/" + roleDTO1.getId())).body(roleDTO1);

    }

    @PutMapping("/role/{roleId}/")
    @Description("Update specific role")
    public ResponseEntity<RoleDTO> put(@RequestBody RoleDTO roleDTO, @PathVariable Long roleId) {
        RoleDTO role = roleService.update(roleDTO);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/role/{roleId}/")
    @Description("Delete the role with role id")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long roleId) {
        roleService.delete(roleId);
        return ResponseEntity.noContent().build();
    }

}
