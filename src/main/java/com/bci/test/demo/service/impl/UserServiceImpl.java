package com.bci.test.demo.service.impl;

import com.bci.test.demo.controller.request.CreateUserRequest;
import com.bci.test.demo.controller.request.PhoneRequest;
import com.bci.test.demo.controller.response.CreateUserResponse;
import com.bci.test.demo.entity.PhoneEntity;
import com.bci.test.demo.entity.UserEntity;
import com.bci.test.demo.repository.UserRepository;
import com.bci.test.demo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public CreateUserResponse saveUser(CreateUserRequest request) {

        List<PhoneRequest> phones = request.getPhones();

      List<PhoneEntity> phonesEntity =  phones.stream()
                .map(phone -> {

                   return PhoneEntity.builder()
                            .number(phone.getNumber())
                            .cityCode(phone.getCityCode())
                            .countryCode(phone.getCountryCode())
                            .build();

                }).collect(Collectors.toList());

        UserEntity userEntity = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phones(phonesEntity)
                .token("service-dummy-token")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(Boolean.TRUE)
                .build();

       UserEntity createdEntity =  userRepository.save(userEntity);

      return  CreateUserResponse.builder()
                .uuid(createdEntity.getId())
                .name(createdEntity.getName())
                .email(createdEntity.getEmail())
                .password(createdEntity.getPassword())
                .created(createdEntity.getCreated())
                .modified(createdEntity.getModified())
                .lastLogin(createdEntity.getLastLogin())
                .isActive(createdEntity.getIsActive())
                .token("dummy token")
                .phones(Arrays.asList(

                        PhoneRequest.builder()
                                .cityCode("1")
                                .countryCode("1")
                                .number("123456")
                                .build(),
                        PhoneRequest.builder()
                                .cityCode("2")
                                .countryCode("2")
                                .number("987654")
                                .build()

                ))
                .build();

    }
}
