package com.bci.test.demo.domain.service;

import com.bci.test.demo.app.api.request.UserRequest;
import com.bci.test.demo.app.api.response.UserResponse;
import com.bci.test.demo.domain.exception.UserException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import java.util.UUID;


public interface UserService {
    UserResponse saveUser(UserRequest request) throws DataIntegrityViolationException;
    UserResponse getUserById(UUID id) throws UserException;
    UserResponse updateUser(UUID id, UserRequest request) throws UserException;

}
