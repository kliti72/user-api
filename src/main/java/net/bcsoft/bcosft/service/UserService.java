package net.bcsoft.bcosft.service;

import jakarta.transaction.Transactional;
import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.repository.RoleRepository;
import net.bcsoft.bcosft.repository.UserRepository;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.NotContextException;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


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

    @Transactional
    public UsersDTO insert(UsersDTO usersDTO) throws NotFoundException {

        Users user = usersDTO.toEntity();


        Role roleCapture = roleRepository.findById(usersDTO.getRoleId())
                .orElseThrow(NotFoundException::new);


        user.setRole(roleCapture);

        Users user2 = userRepository.save(user);

        Users userReq = userRepository.findById(user2.getId())
                .orElseThrow(NotFoundException::new);


        return new UsersDTO(userReq.getId(), userReq.getName(), userReq.getSurname(), userReq.getPassword(), userReq.getRegisterDate(), userReq.getLastAccess(), user.getRole().getId());

    }

    @Transactional
    public UsersDTO update(Long userId, UsersDTO usersDTO) throws NotFoundException {
        Users users = usersDTO.toEntity();
        Users oldUser = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);
        Role roleCapture = roleRepository.findById(usersDTO.getRoleId())
                .orElseThrow(NotFoundException::new);

        users.setRole(roleCapture);

        oldUser.setName(users.getName());
        oldUser.setSurname(users.getSurname());
        oldUser.setRole(users.getRole());
        oldUser.setLastAccess(users.getLastAccess());
        oldUser.setRegisterDate(users.getRegisterDate());
        oldUser.setPassword(users.getPassword());
        Users users1 = userRepository.save(oldUser);

        return new UsersDTO(users1.getId(), users1.getName(), users1.getSurname(), users1.getPassword(), users1.getRegisterDate(), users1.getLastAccess(), users1.getRole().getId());

    }

    public void delete (Long userId) throws NotFoundException {
        Users users = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);

        userRepository.delete(users);
    }

    @Transactional
    public UsersDTO getUserByEmail(String email) throws NotFoundException {
        UsersDTO usersDTO = null;
        
        Users users = userRepository.findByEmail(email);

        if(users.getEmail() == email) {
            return new UsersDTO(users.getId(), users.getName(), users.getSurname(), users.getPassword(), users.getRegisterDate(), users.getLastAccess(), users.getRole().getId());
        } else {
            throw new NotFoundException();
        }

    }



}
