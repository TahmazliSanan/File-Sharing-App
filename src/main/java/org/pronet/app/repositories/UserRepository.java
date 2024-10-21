package org.pronet.app.repositories;

import org.pronet.app.entities.User;
import org.pronet.app.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameContainingIgnoreCase(String username);
    Boolean existsByUsernameContainingIgnoreCase(String username);
    Boolean existsByRole(Role role);
    List<User> findAllByUsernameContainingIgnoreCaseAndIdNot(String username, Long id);
}
