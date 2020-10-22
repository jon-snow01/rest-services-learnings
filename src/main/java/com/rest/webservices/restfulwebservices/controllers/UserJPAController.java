package com.rest.webservices.restfulwebservices.controllers;

import com.rest.webservices.restfulwebservices.beans.User;
import com.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.rest.webservices.restfulwebservices.repositories.UserRepository;
import com.rest.webservices.restfulwebservices.services.UserDaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Api
@RestController
@RequestMapping("/userJpaController")
public class UserJPAController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jpa/users")
    @ApiOperation(nickname = "GetAllUsers ",value = "This method is to get all the users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    @ApiOperation(nickname = "GetUserById",value = "This method is to get user by their userId")
    public Optional<User> retrieveUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("id- "+id);
        return user;
    }

    @PostMapping("/jpa/users")
    @ApiOperation(nickname = "CreateNewUser", value = "Create a new User")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    @ApiOperation(nickname = "DeleteUser",value = "Delete a user by the Id")
    public void deleteUserById(@PathVariable Integer id){
        userRepository.deleteById(id);
    }
}
