package net.bcsoft.bcosft.service;

import net.bcsoft.bcosft.dto.RoleDTO;
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
public class RoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public RoleDTO getRoleByUserId(Long userId) {

        Users user;

        try{
            user = userRepository.findById(userId)
                    .orElseThrow(RuntimeException::new);
        }catch (RuntimeException e){
            throw new InternalException("Utente non trovato");
        }


        if(user.getRole() == null) {
            throw new NullPointerException("Errore nessun ruolo trovato per questo utente");
        }
        Role role;
        try{
            role = roleRepository.findById(user.getRole().getId())
                    .orElseThrow(NotFoundException::new);
        }catch (RuntimeException | NotFoundException e ) {
            throw new InternalException("Errore associazione ruolo-utente");
        }


        return new RoleDTO(role.getId(), role.getName(), role.getAssignRoleBy());
    }

    public List<RoleDTO> getRoles(){

        List<Role> roles;
        try{
            roles = roleRepository.findAll();
        }catch (RuntimeException e){
            throw new InternalException("Errore recupero Ruoli");
        }

        List<RoleDTO> rolesDTO = new ArrayList<>();

        if(roles.isEmpty()) throw new NoContentException("Non sono presenti ruoli");

        for(Role role : roles){
            rolesDTO.add(new RoleDTO(role.getId(), role.getName(), role.getAssignRoleBy()));
        }

        return rolesDTO;
    }

    public RoleDTO insert(RoleDTO roleDTO) {
        Role role = roleDTO.toEntity();
        try{
            roleRepository.save(role);
        }catch (RuntimeException e) {
            throw new InternalException("Errore creazione ruolo");
        }


        Role result;
        try {
            result = roleRepository.findById(role.getId()).orElseThrow(NotFoundException::new);
        }catch (NotFoundException | NotFound e){
            throw new NotFound("Ruolo non trovato");
        }
        if(result == null) throw new BadRequestException("Problema creazione ruolo");
        return new RoleDTO(role.getId(), role.getName(), role.getAssignRoleBy());

    }

    public RoleDTO update(RoleDTO roleDTO) {
        Role role = roleDTO.toEntity();
        Role oldRole;
        try{
            oldRole =  roleRepository.findById(roleDTO.getId()).orElseThrow(NotFoundException::new);
        }catch (RuntimeException | NotFoundException e){
            throw new InternalException("Errore recupero ruolo");
        }
        if(oldRole == null) throw new ConflictException("Errore recupero ruoli");
        Role updateRole;
        try{
            role.setName(roleDTO.getName());
            role.setAssignRoleBy(roleDTO.getAssignRoleBy());
            updateRole = roleRepository.save(role);
        }catch (RuntimeException e) {
            throw new InternalException("Errore recupero Ruoli");
        }

        return new RoleDTO(updateRole.getId(), updateRole.getName(), updateRole.getAssignRoleBy());
    }

    public void delete(Long roleId) {
        Role result;

        try{
            result = roleRepository.findById(roleId)
                    .orElseThrow(NotFoundException::new);
        }catch (NotFoundException e){
            throw new InternalException("Errore recupero ruolo");
        }

        if(result == null) throw new NotFound("ruolo non trovato");

        try{
            roleRepository.delete(result);
        }catch (RuntimeException e){
            throw new ConflictException("Errore cancellazione ruolo");
        }


    }


}