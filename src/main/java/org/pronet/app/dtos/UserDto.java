package org.pronet.app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.pronet.app.enums.Role;

@Data
public class UserDto {
    private Long id;

    @NotNull(message = "Full name cannot be null!")
    @NotBlank(message = "Full name cannot be empty!")
    private String fullName;

    @NotNull(message = "Username cannot be null!")
    @NotBlank(message = "Username cannot be empty!")
    private String username;

    @NotNull(message = "Password cannot be null!")
    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 8, message = "Password must be minimum 8 characters!")
    private String password;
    private Role role;

    public UserDto(Long id, String fullName, String username, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.role = role;
    }
}
