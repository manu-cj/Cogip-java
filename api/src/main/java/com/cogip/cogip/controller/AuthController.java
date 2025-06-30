package com.cogip.cogip.controller;

import com.cogip.cogip.dto.UserRequestDTO;
import com.cogip.cogip.dto.UserResponseDTO;
import com.cogip.cogip.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO userRequestDTO ) {
        UserResponseDTO registeredUser  = userService.create(userRequestDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);

    }

    @GetMapping("/register")
    public String showRegistrationForm() {

        return "registration page";
    }

    @GetMapping("/public")
    public String publicPage() {
        return "This is a public page (accessible to everyone).";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "Welcome to the User Dashboard! You have ROLE_USER or ROLE_ADMIN.";
    }

    @GetMapping("/admin/settings")
    public String adminSettings() {
        return "Welcome to the Admin Settings! You have ROLE_ADMIN.";
    }

    @GetMapping("/admin/users")
    public String adminUsers() {
        return "List of users (Admin only).";
    }
}