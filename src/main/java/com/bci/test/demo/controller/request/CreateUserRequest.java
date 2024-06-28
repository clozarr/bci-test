package com.bci.test.demo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateUserRequest {

    private String name;
    private String email;
    private String password;
    List<PhonesRequest> phones;

}
