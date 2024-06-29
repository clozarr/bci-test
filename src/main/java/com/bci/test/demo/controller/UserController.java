package com.bci.test.demo.controller;


import com.bci.test.demo.controller.request.CreateUserRequest;
import com.bci.test.demo.controller.request.PhoneRequest;
import com.bci.test.demo.controller.response.CreateUserResponse;
import com.bci.test.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){

        log.info("user: {}", request);
        CreateUserResponse response = userService.saveUser(request);
        return new ResponseEntity<CreateUserResponse>(response, HttpStatus.CREATED);
    }



}
