package com.auth_service.auth_service.mappers;


import com.auth_service.auth_service.dto.UserRequestDTO;
import com.auth_service.auth_service.dto.UserResponseDTO;
import com.auth_service.auth_service.models.Role;
import com.auth_service.auth_service.models.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public static User toEntity(UserRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setFirstname(requestDTO.getFirstName());
        user.setLastname(requestDTO.getLastName());
        user.setEmail(requestDTO.getEmail());
        return user;
    }

    /**
     * Convertit une entité User en UserResponseDTO.
     * Note: passwordHash est délibérément omis.
     */
    public static UserResponseDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstname());
        dto.setLastName(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setRegistrationDate(user.getRegistrationDate());

        // Mappage des rôles (suppose que Role a une méthode getName())
        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    /**
     * update entity User wi UserRequestDTO.
     * Use for put/patch operation.
     * Note: password, id, registrationDate et roles is not update here.
     */
    public static void updateEntityFromDto(UserRequestDTO requestDTO, User user) {
        if (requestDTO == null || user == null) {
            return;
        }
        user.setUsername(requestDTO.getUsername());
        user.setFirstname(requestDTO.getFirstName());
        user.setLastname(requestDTO.getLastName());
        user.setEmail(requestDTO.getEmail());
        // Password and role is update with other service
    }
}