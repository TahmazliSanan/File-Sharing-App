package org.pronet.app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
    private Long id;

    @NotNull(message = "Username cannot be null!")
    @NotBlank(message = "Username cannot be empty!")
    private String username;

    @NotNull(message = "Password cannot be null!")
    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 8, message = "Password must be minimum 8 characters!")
    private String password;
}
