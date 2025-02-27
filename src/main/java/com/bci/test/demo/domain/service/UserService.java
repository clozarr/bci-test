package com.bci.test.demo.domain.service;

import com.bci.test.demo.app.api.request.UserRequest;
import com.bci.test.demo.app.api.response.UserResponse;
import com.bci.test.demo.domain.exception.UserException;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.List;
import java.util.UUID;


public interface UserService {
    UserResponse saveUser(UserRequest request) throws DataIntegrityViolationException;
    UserResponse getUserById(UUID id) throws UserException;
    UserResponse updateUser(UUID id, UserRequest request) throws UserException;
    List<UserResponse> getAllUsers() throws UserException;

}
