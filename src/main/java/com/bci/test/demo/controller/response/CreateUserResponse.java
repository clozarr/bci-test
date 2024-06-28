package com.bci.test.demo.controller.response;

import com.bci.test.demo.controller.request.PhonesRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponse {

    private UUID uuid;
    private String name;
    private String email;
    private String password;
    List<PhonesRequest> phones;

    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;
}
