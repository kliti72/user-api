package net.bcsoft.bcosft.controller;

import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NotContextException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<UsersDTO> getById(@PathVariable Long userId) {
        UsersDTO usersDTO = userService.selectById(userId);
        return ResponseEntity.ok(usersDTO);
    }

    @PostMapping("/users/")
    public ResponseEntity<UsersDTO> insert(@RequestBody UsersDTO userDTO) throws URISyntaxException {
        UsersDTO usersDTO = userService.insert(userDTO);
        return ResponseEntity.created(new URI("/users/" + usersDTO.getId())).body(userDTO);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UsersDTO> update (@PathVariable Long userId, @RequestBody UsersDTO user){
        UsersDTO user1 = userService.update(userId, user);
        return ResponseEntity.ok(user1);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId){
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
