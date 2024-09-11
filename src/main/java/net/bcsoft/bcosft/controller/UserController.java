package net.bcsoft.bcosft.controller;

import net.bcsoft.bcosft.entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users/")
    public ResponseEntity<List <Users>>get(){
        //la lista è uguale al metodo di selectAll
        List <Users> userList = new ArrayList<>();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/users/")
    public ResponseEntity<Users> create(@RequestBody Users user) throws URISyntaxException {
        //user1 uguale al metodo di create
        Users user1 = null;
        return ResponseEntity.created(new URI("/users/")).body(user1);
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
