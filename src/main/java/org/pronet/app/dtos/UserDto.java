package org.pronet.app.dtos;

import lombok.Data;
import org.pronet.app.enums.Role;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String username;
    private String password;
    private Role role;

    public UserDto(Long id, String fullName, String username, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.role = role;
    }
}
