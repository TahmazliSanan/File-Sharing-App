package org.pronet.app.helpers;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.User;
import org.pronet.app.enums.Role;
import org.pronet.app.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialDataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Boolean isExistAdmin = userRepository.existsByRole(Role.Admin);
        if (!isExistAdmin) {
            User admin = new User(
                    1L,
                    "Admin of CloudBuddy",
                    "AdminOfCloudBuddy",
                    passwordEncoder.encode("AdminOfCloudBuddy2024"),
                    Role.Admin);
            userRepository.save(admin);
        }
    }
}
