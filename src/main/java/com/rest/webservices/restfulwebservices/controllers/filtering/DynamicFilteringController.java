package com.rest.webservices.restfulwebservices.controllers.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.webservices.restfulwebservices.beans.filtering.DynamicUserCredentials;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/dynamic-filteredCredentials")
public class DynamicFilteringController {

    @GetMapping("/psw-credentials")
    @ApiOperation(nickname = "ViewPassword",value = "This is for viewing password")
    public MappingJacksonValue getPassword(){
        DynamicUserCredentials dynamicUserCredentials = new DynamicUserCredentials("abhishek", "psw321", "1234567890");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("password");
        return createFilterMapping(dynamicUserCredentials,filter);
    }

    @GetMapping("/credentials")
    @ApiOperation(nickname = "ViewCredentials",value = "This is for viewing credentials without password")
    public MappingJacksonValue getCredentials(){
        DynamicUserCredentials dynamicUserCredentials = new DynamicUserCredentials("abhishek", "psw321", "1234567890");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("uuid","mobile");
        return createFilterMapping(dynamicUserCredentials,filter);
    }

    private MappingJacksonValue createFilterMapping(DynamicUserCredentials credentials,SimpleBeanPropertyFilter filter){
        //Creating dynamic filter
        //..[1]..Create a filter..SimpleBeanPropertyFilter
        //..[2]..Create a filter provide..FilterProvide
        //..[3]..Map jackson value to the filtered bean..MappingJacksonValue
        //..[4]..Set filterProvider to the mapping

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicUserCredentialsFilter",filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(credentials);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}
