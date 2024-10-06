package org.pronet.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pronet.app.enums.Role;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Full name cannot be null!")
    @NotBlank(message = "Full name cannot be blank or empty!")
    private String fullName;

    @NotNull(message = "Username cannot be null!")
    @NotBlank(message = "Username cannot be blank or empty!")
    private String username;

    @NotNull(message = "Password cannot be null!")
    @NotBlank(message = "Password cannot be blank or empty!")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
