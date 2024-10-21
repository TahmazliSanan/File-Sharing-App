package org.pronet.app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pronet.app.auth.JwtUtil;
import org.pronet.app.dtos.LoginDto;
import org.pronet.app.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody @Valid LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        ApiResponse apiResponse = new ApiResponse(true, "User logged in successfully!", jwt);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
