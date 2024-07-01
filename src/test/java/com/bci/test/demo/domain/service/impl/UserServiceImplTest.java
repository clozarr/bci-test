package com.bci.test.demo.domain.service.impl;

import com.bci.test.demo.DataHelper;
import com.bci.test.demo.app.api.request.UserRequest;
import com.bci.test.demo.app.api.response.UserResponse;
import com.bci.test.demo.domain.entity.UserEntity;
import com.bci.test.demo.domain.mapper.UserMapper;
import com.bci.test.demo.domain.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private Validator validator;
    @Mock
    private ConstraintViolation<UserRequest> constraintViolation;


    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
        constraintViolation =mock(ConstraintViolation.class);
        userService = new UserServiceImpl(userRepository,userMapper,validator);

    }

    @Test
    void test_save_user_successfully() {

        //Set Data
        Set<ConstraintViolation<UserRequest>> violations = Collections.emptySet();
        UserRequest request = DataHelper.getUserRequest();
        UserEntity createdUserEntity = DataHelper.getCreatedEntity();

        //execute
        when(validator.validate(any(UserRequest.class))).thenReturn(violations);
        when(userRepository.save(any(UserEntity.class))).thenReturn(createdUserEntity);
        UserResponse response = userService.saveUser(request);

        //Assertions
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getUuid());
        Assertions.assertEquals(response.getName(),"Created Dummy");
        Assertions.assertEquals(response.getEmail(),"dummy@email.com");
        Assertions.assertEquals(response.getPassword(),"Hunter3*");
        Assertions.assertEquals(response.getToken(),"dummy-token");
        Assertions.assertEquals(response.getIsActive(), Boolean.TRUE);
        Assertions.assertNotNull(response.getPhones());
        Assertions.assertEquals(response.getPhones().isEmpty(), Boolean.FALSE);

    }

    @Test
    void test_save_user_failed_when_invalid_email() {

        //Set Data
        UserRequest request = DataHelper.getUserRequest();
        request.setEmail("wrong email");
        Set<ConstraintViolation<UserRequest>> violations = Set.of(constraintViolation);
        when(constraintViolation.getMessage()).thenReturn("Invalid email");
        when(validator.validate(any(UserRequest.class))).thenReturn(violations);

       ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
               () -> userService.saveUser(request));

       Assertions.assertTrue( exception.getMessage().contains("Invalid email"));
    }

    @Test
    void test_save_user_failed_when_invalid_password_at_least_one_number() {

        //Set Data
        UserRequest request = DataHelper.getUserRequest();
        request.setPassword("wrong pass");
        Set<ConstraintViolation<UserRequest>> violations = Set.of(constraintViolation);
        when(constraintViolation.getMessage()).thenReturn("password must contain at least one number");
        when(validator.validate(any(UserRequest.class))).thenReturn(violations);

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
                () -> userService.saveUser(request));

        Assertions.assertTrue( exception.getMessage().contains("password must contain at least one number"));
    }

    @Test
    void test_save_user_failed_when_invalid_password_at_least_one_especial_character() {

        //Set Data
        UserRequest request = DataHelper.getUserRequest();
        request.setPassword("wrong4pass");
        Set<ConstraintViolation<UserRequest>> violations = Set.of(constraintViolation);
        when(constraintViolation.getMessage()).thenReturn("password must contain at least one special character");
        when(validator.validate(any(UserRequest.class))).thenReturn(violations);

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
                () -> userService.saveUser(request));

        Assertions.assertTrue( exception.getMessage().contains("password must contain at least one special character"));
    }
}