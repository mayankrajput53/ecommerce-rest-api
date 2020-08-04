package com.example.security.backend.service;

import com.example.security.backend.exception.RecordNotFoundException;
import com.example.security.backend.model.Role;
import com.example.security.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRoles() {

        List<Role> roleList = roleRepository.findAll();

        if (roleList.size() > 0) {
            return roleList;
        } else {
            return new ArrayList<>();
        }
    }

    public Role getRoleById(Long id) throws RecordNotFoundException {

        Optional<Role> role = roleRepository.findById(id);

        if (role.isPresent()) {
            return role.get();
        } else {
            throw new RecordNotFoundException("No Role record exist for given id");
        }
    }

    public Role createOrUpdate(Role role) throws RecordNotFoundException {

        Optional<Role> optionalRole = roleRepository.findById(role.getId());

        if (optionalRole.isPresent()) {
            Role newEntity = optionalRole.get();
            newEntity.setName(role.getName());
            newEntity = roleRepository.save(newEntity);
            return newEntity;
        } else {
            role = roleRepository.save(role);
            return role;
        }
    }

    public void deleteRoleById(Long id) throws RecordNotFoundException{

        Optional<Role> entity = roleRepository.findById(id);

        if(entity.isPresent()) {
            roleRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("No Role record exist for given id");
        }

    }

}
