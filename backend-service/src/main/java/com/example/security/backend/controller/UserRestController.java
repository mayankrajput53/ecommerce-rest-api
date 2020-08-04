package com.example.security.backend.controller;

import com.example.security.backend.exception.RecordNotFoundException;
import com.example.security.backend.model.User;
import com.example.security.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> userList = userService.getAllUsers();

        return new ResponseEntity<>(userList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws RecordNotFoundException{

        User user = userService.getUserById(id);

        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/registration")

    public ResponseEntity<User> createOrUpdateUser(@RequestBody User theUser) throws RecordNotFoundException {

        theUser.setId((long) 0);

        User user = userService.createOrUpdate(theUser);

        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }

}
