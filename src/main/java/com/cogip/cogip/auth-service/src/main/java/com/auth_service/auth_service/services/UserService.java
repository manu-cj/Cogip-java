package com.auth_service.auth_service.services;

import com.auth_service.auth_service.dto.UserRequestDTO;
import com.auth_service.auth_service.dto.UserResponseDTO;
import com.auth_service.auth_service.mappers.UserMapper;
import com.auth_service.auth_service.models.Role;
import com.auth_service.auth_service.models.User;
import com.auth_service.auth_service.repository.RoleRepository;
import com.auth_service.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public UserResponseDTO create(UserRequestDTO requestDTO) {
        User user = UserMapper.toEntity(requestDTO);

        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());

        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role 'ROLE_USER' not found. Please ensure it exists."));
        user.addRole(defaultRole);

        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

}
