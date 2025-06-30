package com.cogip.cogip.services;

import com.cogip.cogip.dto.UserRequestDTO;
import com.cogip.cogip.dto.UserResponseDTO;
import com.cogip.cogip.mappers.UserMapper;
import com.cogip.cogip.models.Role;
import com.cogip.cogip.models.User;
import com.cogip.cogip.repository.RoleRepository;
import com.cogip.cogip.repository.UserRepository;
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
