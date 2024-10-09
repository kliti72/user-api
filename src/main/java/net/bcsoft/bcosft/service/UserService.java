package net.bcsoft.bcosft.service;

import jakarta.transaction.Transactional;
import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.entity.Role;
import net.bcsoft.bcosft.entity.Users;
import net.bcsoft.bcosft.exception.*;
import net.bcsoft.bcosft.repository.RoleRepository;
import net.bcsoft.bcosft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public List<UsersDTO> selectAll() {
        List <Users> usersList;
        try{
            usersList = userRepository.findAll();
        }catch (RuntimeException e){
            throw new InternalException("Errore recupero utenti");
        }
        List <UsersDTO> usersDTOList = new ArrayList<>();

        if(usersList.isEmpty()) {
            throw new NoContentException("Non presenti user");
        }
        for (Users users : usersList) {
            UsersDTO usersDTO = new UsersDTO(users.getId(), users.getName(), users.getSurname(), users.getEmail(), users.getPassword(), users.getRegisterDate(), users.getLastAccess(), users.getRole().getId());
            usersDTOList.add(usersDTO);
        }
        return usersDTOList;
    }

    public UsersDTO selectById(Long userId) {
        Users users;
        try {
            users = userRepository.findById(userId)
                    .orElseThrow(NotFoundException::new);
        }catch (RuntimeException | NotFoundException e ) {
            throw new InternalException("Errore recupero utenti");
        }

        return new UsersDTO(users.getId(), users.getName(), users.getSurname(), users.getEmail(), users.getPassword(), users.getRegisterDate(), users.getLastAccess(), users.getRole().getId());
    }

    @Transactional
    public UsersDTO insert(UsersDTO usersDTO) {
        Users user = usersDTO.toEntity();
        Role roleCapture;

        try{
            roleCapture = roleRepository.findById(usersDTO.getRoleId())
                    .orElseThrow(NotFoundException::new);
        }catch (NotFoundException e){
            throw new InternalException("RUolo non trovato");
        }
        user.setRole(roleCapture);
        Users result;

        try{
            result = userRepository.save(user);
        }catch (RuntimeException e){
            throw new InternalException("Errore creazione utente");
        }
        Users userReq;
        try {
            userReq = userRepository.findById(result.getId())
                    .orElseThrow(NotFoundException::new);
        } catch (NotFound | NotFoundException e){
            throw new NotFound("Utente non trovato");
        }
         if(userReq == null) throw new BadRequestException("Problema creazione utente");



         return new UsersDTO(userReq.getId(), userReq.getName(), userReq.getEmail(), userReq.getSurname(), userReq.getPassword(), userReq.getRegisterDate(), userReq.getLastAccess(), userReq.getRole().getId());
    }

    @Transactional
    public UsersDTO update(Long userId, UsersDTO usersDTO) {
        Users users = usersDTO.toEntity();
        Users oldUser;
        Role roleCapture;

        try{
            oldUser = userRepository.findById(userId)
                    .orElseThrow(NotFoundException::new);
            roleCapture = roleRepository.findById(usersDTO.getRoleId())
                    .orElseThrow(NotFoundException::new);
        }catch (RuntimeException | NotFoundException e){
            throw new InternalException("Errore recupero user");
        }

        if(oldUser == null) throw new ConflictException("Errore recupero user");
        users.setRole(roleCapture);
        Users updateUser;
        try {
            oldUser.setName(users.getName());
            oldUser.setSurname(users.getSurname());
            oldUser.setRole(users.getRole());
            oldUser.setLastAccess(users.getLastAccess());
            oldUser.setRegisterDate(users.getRegisterDate());
            oldUser.setPassword(users.getPassword());
            updateUser = userRepository.save(oldUser);
        }catch (RuntimeException e){
            throw new InternalException("errore aggiornamento utente");
        }
        return new UsersDTO(updateUser.getId(), updateUser.getName(), updateUser.getName(), updateUser.getSurname(), updateUser.getPassword(), updateUser.getRegisterDate(), updateUser.getLastAccess(), updateUser.getRole().getId());

    }

    public void delete (Long userId){
        Users result;

        try{
            result = userRepository.findById(userId)
                    .orElseThrow(NotFoundException::new);
        }catch (NotFoundException e){
            throw new InternalException("Errore recupero utrente");
        }
        if(result == null) throw new NotFound("Utente non trovato");

        try{
            userRepository.delete(result);
        }catch (RuntimeException e){
            throw new ConflictException("Errore cancellazione ruolo");
        }

    }

    @Transactional
    public UsersDTO getUserByEmail(String email) {
        Users user;

        try {
            user = userRepository.findByEmail(email);
        }catch (RuntimeException e){
            throw new InternalException("Utente non trovato");
        }

        if(user == null){
            throw new NotFound("utente non trovato");
        }
        return new UsersDTO(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getRegisterDate(), user.getLastAccess(), user.getRole().getId());


    }



}
