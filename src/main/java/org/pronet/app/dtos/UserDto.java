package org.pronet.app.dtos;

import lombok.Data;
import org.pronet.app.enums.Role;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String username;
    private Role role;
}
