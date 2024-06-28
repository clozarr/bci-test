package com.bci.test.demo.controller;


import com.bci.test.demo.controller.request.CreateUserRequest;
import com.bci.test.demo.controller.request.PhonesRequest;
import com.bci.test.demo.controller.response.CreateUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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



   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){

       //log.info("user: {}", request);
       CreateUserResponse response = CreateUserResponse.builder()
               .uuid(UUID.randomUUID())
               .name("Dummy Name")
               .email("correo@correo.com")
               .password("abc123*")
               .created(LocalDateTime.now())
               .modified(LocalDateTime.now())
               .lastLogin(LocalDateTime.now())
               .isActive(Boolean.TRUE)
               .token("dummy token")
               .phones(Arrays.asList(

                       PhonesRequest.builder()
                               .cityCode("1")
                               .countryCode("1")
                               .number("123456")
                               .build(),
                       PhonesRequest.builder()
                               .cityCode("2")
                               .countryCode("2")
                               .number("987654")
                               .build()

                       ))
               .build();

        return new ResponseEntity<CreateUserResponse>(response, HttpStatus.CREATED);
    }



}
