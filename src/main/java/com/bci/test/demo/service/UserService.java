package com.bci.test.demo.service;

import com.bci.test.demo.controller.request.CreateUserRequest;
import com.bci.test.demo.controller.response.CreateUserResponse;
import org.springframework.stereotype.Service;


public interface UserService {
    CreateUserResponse saveUser(CreateUserRequest request);

}
