package org.pronet.app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pronet.app.dtos.UserDto;
import org.pronet.app.responses.ApiResponse;
import org.pronet.app.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody @Valid UserDto userDTO) {
        UserDto createdUser = userService.createUser(userDTO);
        ApiResponse apiResponse = new ApiResponse(true, "User created successfully!", createdUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable(value = "id") Long id) {
        UserDto foundUser = userService.getUserById(id);
        ApiResponse apiResponse = new ApiResponse(true, "User found!", foundUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ApiResponse> getUsers() {
        List<UserDto> userList = userService.getUsers();
        ApiResponse apiResponse = new ApiResponse(true, "User list", userList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ApiResponse> updateUserById(
            @PathVariable(value = "id") Long id,
            @RequestBody @Valid UserDto userDTO) {
        UserDto updatedUser = userService.updateUserById(id, userDTO);
        ApiResponse apiResponse = new ApiResponse(true, "User updated successfully!", updatedUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable(value = "id") Long id) {
        userService.deleteUserById(id);
        ApiResponse apiResponse = new ApiResponse(true, "User deleted successfully!", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
