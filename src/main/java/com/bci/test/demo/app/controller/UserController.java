package com.bci.test.demo.app.controller;


import com.bci.test.demo.app.api.request.UserRequest;
import com.bci.test.demo.app.api.response.UserResponse;
import com.bci.test.demo.domain.exception.ErrorResponse;
import com.bci.test.demo.domain.exception.UserException;
import com.bci.test.demo.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Management System", description = "Operations pertaining to user in User Management System")
public class UserController {

    @Autowired
    private UserService userService;


    @Operation(summary = "Create a new user", description = "Allow to create a new user in the user management system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request or validation error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(

            @Parameter(name = "request", required = true, description = "User object to save")
            @Valid  @RequestBody UserRequest request){

        log.info("Create user: {}", request);
        UserResponse response = userService.saveUser(request);
        return new ResponseEntity<UserResponse>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing user", description = "Allow to update an user in the user management system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request or validation error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping(path = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> updateUser(

            @Parameter(name = "idUser", required = true, description = "User id to update")
            @PathVariable("id") String idUser,

            @Parameter(name = "request", required = true, description = "User object to update")
            @Valid @RequestBody UserRequest request) throws UserException {

        log.info("Update user: {}", request);
        UUID id = UUID.fromString(idUser);
        UserResponse response = userService.updateUser(id,request);
        return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
    }


    @Operation(summary = "Get user by id", description = "Gets the information of a user given their id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request or validation error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(path = "/{id}"/*, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<UserResponse> getUser(

            @Parameter(name = "idUser", required = true, description = "User id to get information")
            @PathVariable("id") String idUser) throws UserException {


        log.info("Get user by id: {}", idUser);
        UUID id = UUID.fromString(idUser);
        UserResponse userResponse = userService.getUserById(id);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);

    }





}
