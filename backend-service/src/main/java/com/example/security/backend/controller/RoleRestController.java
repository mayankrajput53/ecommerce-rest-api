package com.example.security.backend.controller;

import com.example.security.backend.exception.RecordNotFoundException;
import com.example.security.backend.model.Role;
import com.example.security.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {

        List<Role> roleList = roleService.getAllRoles();

        return new ResponseEntity<>(roleList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) throws RecordNotFoundException {

        Role role = roleService.getRoleById(id);

        return new ResponseEntity<>(role, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Role> createOrUpdate(@RequestBody Role theRole) throws RecordNotFoundException {

        theRole.setId((long) 0);

        Role role = roleService.createOrUpdate(theRole);

        return new ResponseEntity<>(role, new HttpHeaders(), HttpStatus.OK);
    }

    public HttpStatus deleteRoleById(@PathVariable("id") Long id) throws RecordNotFoundException{

        roleService.deleteRoleById(id);

        return HttpStatus.FORBIDDEN;
    }

}
