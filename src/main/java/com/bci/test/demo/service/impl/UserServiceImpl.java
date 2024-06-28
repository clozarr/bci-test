package com.bci.test.demo.service.impl;

import com.bci.test.demo.controller.request.CreateUserRequest;
import com.bci.test.demo.controller.response.CreateUserResponse;
import com.bci.test.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public CreateUserResponse saveUser(CreateUserRequest request) {
        return null;
    }
}
