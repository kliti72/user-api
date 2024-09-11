package net.bcsoft.bcosft.controller;

import net.bcsoft.bcosft.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users/")
    public ResponseEntity<List <User>>get(){
        //la lista è uguale al metodo di selectAll
        List <User> userList = new ArrayList<>();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/users/")
    public ResponseEntity<User> create(@RequestBody User user) throws URISyntaxException {
        //user1 uguale al metodo di create
        User user1 = null;
        return ResponseEntity.created(new URI("/users/")).body(user1);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> update (@PathVariable Long userId, @RequestBody User user){
        //nell'oggetto user1 metterlo uguale al metodo di update
        User user1 = null;
        return ResponseEntity.ok(user1);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId){
        //mettere qui il metodo di delete
        return ResponseEntity.noContent().build();
    }
}
