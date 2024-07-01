package com.bci.test.demo.app.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UserRequest {

    @NotBlank(message = "Name can't be empty")
    @NotNull(message = "Name can't be empty")
    @NotBlank(message = "Name can't be empty")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email can't be empty")
    private String email;

    @Size(min = 8, message = "El password debe tener al menos 8 caracteres")
    @Pattern.List({
            @Pattern(regexp = "(?=.*[0-9]).+", message = "password must contain at least one number"),
            @Pattern(regexp = "(?=.*[a-z]).+", message = "Password must contain at least one lowercase letter"),
            @Pattern(regexp = "(?=.*[A-Z]).+", message = "Password must contain at least one uppercase letter"),
            @Pattern(regexp = "(?=.*[!@#$%^&*()\\-_=+{};:,<.>]).+", message = "The password must contain at least one special character")
            })
    private String password;
    List<PhoneRequest> phones;

}
