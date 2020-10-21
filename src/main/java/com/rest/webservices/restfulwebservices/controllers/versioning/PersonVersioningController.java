package com.rest.webservices.restfulwebservices.controllers.versioning;

import com.rest.webservices.restfulwebservices.beans.versioning.Name;
import com.rest.webservices.restfulwebservices.beans.versioning.PersonV1;
import com.rest.webservices.restfulwebservices.beans.versioning.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    //Method 1
    @GetMapping("v1/person")
    public PersonV1 getPersonV1(){
        return new PersonV1("Abhishek Anand");
    }

    @GetMapping("v2/person")
    public PersonV2 getPersonV2(){
        return new PersonV2(new Name("Abhishek","Anand"));
    }

    //Method 2
    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 getParamV1(){
        return new PersonV1("Abhishek Anand");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 getParamV2(){
        return new PersonV2(new Name("Abhishek","Anand"));
    }

    //Method 3
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getHeaderV1(){
        return new PersonV1("Abhishek Anand");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getHeaderV2(){
        return new PersonV2(new Name("Abhishek","Anand"));
    }
}
