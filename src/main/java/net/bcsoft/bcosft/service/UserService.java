package net.bcsoft.bcosft.service;

import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.repository.UserRepository;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.NotContextException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UsersDTO> selectAll() throws NotContextException {
        List <Users> usersList;
        try{
            usersList = userRepository.findAll();
        }catch (RuntimeException e){
            throw new InternalException("Errore");
        }
        List <UsersDTO> usersDTOList = new ArrayList<>();

        if(usersList.isEmpty()) {
            throw new NotContextException("Non presenti user");
        }
        for (Users users : usersList) {
            UsersDTO usersDTO = new UsersDTO(users.getId(), users.getName(), users.getSurname(), users.getPassword(), users.getRegisterDate(), users.getLastAccess(), users.getRole().getId());
            usersDTOList.add(usersDTO);
        }
        return usersDTOList;
    }

    public UsersDTO selectById(Long userId) throws NotFoundException {
        Users users;
        users = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);
        return new UsersDTO(users.getId(), users.getName(), users.getSurname(), users.getPassword(), users.getRegisterDate(), users.getLastAccess(), users.getRole().getId());
    }
}
