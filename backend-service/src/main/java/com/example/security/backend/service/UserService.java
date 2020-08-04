package com.example.security.backend.service;

import com.example.security.backend.exception.RecordNotFoundException;
import com.example.security.backend.model.User;
import com.example.security.backend.repository.RoleRepository;
import com.example.security.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<User> getAllUsers() {

        List<User> userList = userRepository.findAll();

        if (userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<>(0);
        }
    }

    public User getUserById(Long id) throws RecordNotFoundException {

        Optional<User> entity = userRepository.findById(id);

        if(entity.isPresent()) {
            return entity.get();
        }
        else {
            throw new RecordNotFoundException("No User record exist for given id");
        }

    }

    public User createOrUpdate(User user) throws RecordNotFoundException {

        Optional<User> entity = userRepository.findById(user.getId());

        if(entity.isPresent()) {
            User newEntity = entity.get();
            newEntity.setFirstName(user.getFirstName());
            newEntity.setFirstName(user.getLastName());
            newEntity.setPassword(user.getPassword());
            newEntity.setUserName(user.getUserName());
            newEntity.setRoles(new HashSet<>(roleRepository.findAll()));

            newEntity = userRepository.save(newEntity);
            return newEntity;
        }
        else {
            user.setRoles(new HashSet<>(roleRepository.findAll()));
            user = userRepository.save(user);
            return user;
        }
    }

}
