package net.bcsoft.bcosft.controller;

import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NotContextException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users/")
    public ResponseEntity<List <UsersDTO>>get(){
        //la lista è uguale al metodo di selectAll
        List <UsersDTO> userList = new ArrayList<>();
        try{
           userList  = userService.selectAll();
        }catch (NotContextException e){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(userList);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UsersDTO> getById(@PathVariable Long userId) {
        UsersDTO usersDTO;

        try {
            usersDTO = userService.selectById(userId);
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(usersDTO);
    }

    @PostMapping("/users/")
    public ResponseEntity<UsersDTO> insert(@RequestBody UsersDTO userDTO)  {

        UsersDTO usersDTO;

        try {
            usersDTO = userService.insert(userDTO);
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(usersDTO);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<Users> update (@PathVariable Long userId, @RequestBody Users user){
        //nell'oggetto user1 metterlo uguale al metodo di update
        Users user1 = null;
        return ResponseEntity.ok(user1);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId){
        //mettere qui il metodo di delete
        return ResponseEntity.noContent().build();
    }
}
