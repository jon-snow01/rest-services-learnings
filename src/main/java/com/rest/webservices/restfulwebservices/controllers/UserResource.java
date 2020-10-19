package com.rest.webservices.restfulwebservices.controllers;

import com.rest.webservices.restfulwebservices.beans.User;
import com.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.rest.webservices.restfulwebservices.services.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = userDaoService.findOne(id);
        if(!Optional.ofNullable(user).isPresent()){
            throw new UserNotFoundException("id - "+id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
