package com.bci.test.demo.domain.service.impl;

import com.bci.test.demo.app.api.request.UserRequest;
import com.bci.test.demo.app.api.response.UserResponse;
import com.bci.test.demo.domain.entity.UserEntity;
import com.bci.test.demo.domain.exception.UserException;
import com.bci.test.demo.domain.mapper.UserMapper;
import com.bci.test.demo.domain.repository.UserRepository;
import com.bci.test.demo.domain.service.UserService;
import com.bci.test.demo.utils.ExceptionMessage;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    @Transactional
    public UserResponse saveUser(UserRequest request) throws DataIntegrityViolationException {

        Set<ConstraintViolation<UserRequest>> violations = validator.validate(request);

       /* if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }*/

        UserEntity userEntity = userMapper.toEntity(request);
        UserEntity createdEntity =  userRepository.save(userEntity);
        return  userMapper.toResponse(createdEntity);

    }

    @Override
    public UserResponse getUserById(UUID userId) throws UserException {

        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        UserEntity userEntity = userEntityOptional.orElseThrow(() -> {

          log.info("Error getUserById: {}", userId.toString());
           return UserException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(ExceptionMessage.USER_NOT_FOUND)
                    .build();
                }
        );

        return userMapper.toResponse(userEntity);
    }


    @Override
    public UserResponse updateUser(UUID userId, UserRequest request) throws UserException {

        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        UserEntity userEntity = userEntityOptional.orElseThrow(() -> {

                    log.info("Error getUserById: {}", userId.toString());
                    return UserException.builder()
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .message(ExceptionMessage.USER_NOT_FOUND)
                            .build();
                }
        );

        userEntity.setModified(LocalDateTime.now());
        userEntity.setLastLogin(LocalDateTime.now());
        userEntity.setName(request.getName());
        userEntity.setPassword(request.getPassword());
        userEntity.setEmail(request.getEmail());

        return  userMapper.toResponse(userRepository.save(userEntity));
    }
}
