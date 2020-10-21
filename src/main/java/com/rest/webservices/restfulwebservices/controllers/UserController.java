package com.rest.webservices.restfulwebservices.controllers;

import com.rest.webservices.restfulwebservices.beans.User;
import com.rest.webservices.restfulwebservices.services.UserDaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Api
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    @ApiOperation(nickname = "GetAllUsers ",value = "This method is to get all the users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    @ApiOperation(nickname = "GetUserById",value = "This method is to get user by their userId")
    public User retrieveUser(@PathVariable int id){
        User user = userDaoService.findOne(id);
        return user;
    }

    @PostMapping("/users")
    @ApiOperation(nickname = "CreateNewUser", value = "Create a new User")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    @ApiOperation(nickname = "DeleteUser",value = "Delete a user by the Id")
    public User deleteUserById(@PathVariable Integer id){
        User user = userDaoService.deleteUserById(id);
        return user;
    }
}
