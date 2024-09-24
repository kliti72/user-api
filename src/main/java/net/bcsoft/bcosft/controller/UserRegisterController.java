package net.bcsoft.bcosft.controller;


import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.repository.UserRepository;
import net.bcsoft.bcosft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterController {


    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user/register")
    public ResponseEntity<Boolean> register(@RequestBody UsersDTO user) {

        try{
            userService.insert(user);
        }catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException x) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
