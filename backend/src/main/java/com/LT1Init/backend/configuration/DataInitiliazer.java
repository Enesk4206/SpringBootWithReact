package com.LT1Init.backend.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.LT1Init.backend.model.Role;
import com.LT1Init.backend.model.User;
import com.LT1Init.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitiliazer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args){
        if(!userRepository.existsByUsername("admin")){
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123admin"));
            admin.setEmail("admin@example.com");
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
        }
    }
}
