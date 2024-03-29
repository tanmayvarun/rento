package com.rento.users.controller;


import com.rento.users.models.*;
import com.rento.users.services.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/v1")
@Slf4j
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register/tenant", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Register Tenant")
    public ResponseEntity<User> register(CreateTenantRequest request) {
        User tenant = userService.createUser(CreateUserRequest.builder()
                .countryCode(request.getCountryCode())
                .phoneNumber(request.getPhoneNumber())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .type(UserType.TENANT)
                .build());
        return new ResponseEntity<>(tenant, HttpStatus.OK);
    }

    @RequestMapping(value = "/register/owner", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Register Owner")
    public ResponseEntity<User> register(CreateOwnerRequest request) {
        User owner = userService.createUser(CreateUserRequest.builder()
                .countryCode(request.getCountryCode())
                .phoneNumber(request.getPhoneNumber())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .type(UserType.OWNER)
                .build());
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{countryCode}/{phoneNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Get user from phonenumber")
    public ResponseEntity<User> getUserFromPhoneNumber(
            @PathVariable("countryCode") String countryCode,
            @PathVariable("phoneNumber") String phoneNumber) {
        Optional<User> userOptional = userService.getUserByPhoneNumber(countryCode, phoneNumber);
        return new ResponseEntity<User>(userOptional.orElse(null), HttpStatus.OK);

    }
}
