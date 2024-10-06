package org.pronet.app.services;

import org.pronet.app.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getUsers();
    UserDto updateUserById(Long id, UserDto userDto);
    void deleteUserById(Long id);
}
