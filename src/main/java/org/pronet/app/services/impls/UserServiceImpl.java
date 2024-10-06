package org.pronet.app.services.impls;

import lombok.RequiredArgsConstructor;
import org.pronet.app.dtos.UserDto;
import org.pronet.app.entities.User;
import org.pronet.app.exceptions.ResourceAlreadyExistsException;
import org.pronet.app.exceptions.ResourceNotFoundException;
import org.pronet.app.repositories.UserRepository;
import org.pronet.app.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        Boolean existsByUsername = userRepository.existsByUsernameContainingIgnoreCase(userDto.getUsername());
        if (!existsByUsername) {
            User newUser = new User();
            newUser.setFullName(userDto.getFullName());
            newUser.setUsername(userDto.getUsername());
            newUser.setPassword(userDto.getPassword());
            newUser.setRole(userDto.getRole());
            userRepository.save(newUser);
            return new UserDto(
                    newUser.getId(),
                    newUser.getFullName(),
                    newUser.getUsername(),
                    newUser.getRole());
        }
        throw new ResourceAlreadyExistsException("User already exists with username: " + userDto.getUsername());
    }

    @Override
    public UserDto getUserById(Long id) {
        User foundUser = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return new UserDto(
                foundUser.getId(),
                foundUser.getFullName(),
                foundUser.getUsername(),
                foundUser.getRole());
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> userList = userRepository.findAll();
        return userList
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getFullName(),
                        user.getUsername(),
                        user.getRole()))
                .toList();
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userDto) {
        User foundUser = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        List<User> takenUsernameList = userRepository.findAllByUsernameContainingIgnoreCaseAndIdNot(userDto.getUsername(), id);
        foundUser.setFullName(userDto.getFullName());
        foundUser.setRole(userDto.getRole());
        if (takenUsernameList.isEmpty()) {
            foundUser.setUsername(userDto.getUsername());
        } else {
            throw new ResourceAlreadyExistsException("Username already taken!");
        }
        userRepository.save(foundUser);
        return new UserDto(
                foundUser.getId(),
                foundUser.getFullName(),
                foundUser.getUsername(),
                foundUser.getRole());
    }

    @Override
    public void deleteUserById(Long id) {
        User foundUser = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        userRepository.delete(foundUser);
    }
}
