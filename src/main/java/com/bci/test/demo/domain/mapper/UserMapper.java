package com.bci.test.demo.domain.mapper;

import com.bci.test.demo.app.api.request.PhoneRequest;
import com.bci.test.demo.app.api.request.UserRequest;
import com.bci.test.demo.app.api.response.UserResponse;
import com.bci.test.demo.domain.entity.PhoneEntity;
import com.bci.test.demo.domain.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserEntity toEntity(UserRequest userRequest){

        List<PhoneRequest> phones = userRequest.getPhones();

        UserEntity userEntity = UserEntity.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .token("service-dummy-token")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(Boolean.TRUE)
                .build();

        List<PhoneEntity> phonesEntity =  phones.stream()
                .map(phone -> {

                    return PhoneEntity.builder()
                            .number(phone.getNumber())
                            .cityCode(phone.getCityCode())
                            .countryCode(phone.getCountryCode())
                            .user(userEntity)
                            .build();

                }).collect(Collectors.toList());

        userEntity.setPhones(phonesEntity);

        return userEntity;

    }


    public UserResponse toResponse(UserEntity userEntity){

        return  UserResponse.builder()
                .uuid(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .created(userEntity.getCreated())
                .modified(userEntity.getModified())
                .lastLogin(userEntity.getLastLogin())
                .isActive(userEntity.getIsActive())
                .token(userEntity.getToken())
                .phones(
                        userEntity.getPhones()
                                .stream()
                                .map(phoneEntity -> {

                                    return PhoneRequest.builder()
                                            .number(phoneEntity.getNumber())
                                            .countryCode(phoneEntity.getCountryCode())
                                            .cityCode(phoneEntity.getCityCode())
                                            .build();

                                }).collect(Collectors.toList())
                )
                .build();
    }


}
