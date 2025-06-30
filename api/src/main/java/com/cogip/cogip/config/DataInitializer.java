package com.cogip.cogip.config;

import com.cogip.cogip.models.Role;
import com.cogip.cogip.models.User;
import com.cogip.cogip.repository.RoleRepository;
import com.cogip.cogip.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Create user and admin by default
 */
@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initData(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User("user", passwordEncoder.encode("password"));
                user.addRole(userRole);
                userRepository.save(user);
                System.out.println("User 'user' created with ROLE_USER.");
            }

            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User("admin", passwordEncoder.encode("adminpassword"));
                admin.addRole(adminRole);
                userRepository.save(admin);
                System.out.println("Admin 'admin' created with RoLE_ADMIN");
            }
        };
    }
}
