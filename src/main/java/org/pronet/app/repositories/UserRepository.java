package org.pronet.app.repositories;

import org.pronet.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsernameContainingIgnoreCase(String username);
    List<User> findAllByUsernameContainingIgnoreCaseAndIdNot(String username, Long id);
}
