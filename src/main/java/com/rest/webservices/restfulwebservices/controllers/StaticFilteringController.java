package com.rest.webservices.restfulwebservices.controllers;

import com.rest.webservices.restfulwebservices.beans.StaticUserCredentials;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/static-filteredCredentials")
public class StaticFilteringController {

    @GetMapping("/credentials")
    @ApiOperation(nickname = "viewCredentials",value = "This is for viewing user credentials")
    public StaticUserCredentials viewUserCredentials(){
        return new StaticUserCredentials("anand","psw123","9876543210");
    }


}
