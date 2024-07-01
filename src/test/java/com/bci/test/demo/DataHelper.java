package com.bci.test.demo;

import com.bci.test.demo.app.api.request.PhoneRequest;
import com.bci.test.demo.app.api.request.UserRequest;
import com.bci.test.demo.domain.entity.PhoneEntity;
import com.bci.test.demo.domain.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class DataHelper {

    public static UserRequest getUserRequest(){


     return UserRequest.builder()
                .name("Created Dummy")
                .email("dummy@email.com")
                .password("Hunter3*")
                .phones(Arrays.asList(
                        PhoneRequest.builder()
                                .number("12345")
                                .cityCode("4")
                                .countryCode("57")
                                .build(),
                        PhoneRequest.builder()
                                .number("67890")
                                .cityCode("5")
                                .countryCode("58")
                                .build()))
                .build();
    }

    public static UserEntity getEntity(){

       return UserEntity.builder()
                .name("Created Dummy")
                .email("dummy@email.com")
                .password("Hunter3*")
                .token("dummy-token")
                .isActive(Boolean.TRUE)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .phones(Arrays.asList(
                        PhoneEntity.builder()
                                .number("12345")
                                .cityCode("4")
                                .countryCode("57")
                                .build(),
                        PhoneEntity.builder()
                                .number("67890")
                                .cityCode("5")
                                .countryCode("58")
                                .build()))
                .build();

    }

    public static UserEntity getCreatedEntity(){

        return UserEntity.builder()
                .id(UUID.fromString("0858ef3c-ac80-49cd-8f89-aa3dd673d4ec"))
                .name("Created Dummy")
                .email("dummy@email.com")
                .password("Hunter3*")
                .token("dummy-token")
                .isActive(Boolean.TRUE)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .phones(Arrays.asList(
                        PhoneEntity.builder()
                                .number("12345")
                                .cityCode("4")
                                .countryCode("57")
                                .build(),
                        PhoneEntity.builder()
                                .number("67890")
                                .cityCode("5")
                                .countryCode("58")
                                .build()))
                .build();

    }
}
