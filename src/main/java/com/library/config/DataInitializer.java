package com.library.config;

import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository) {
        return args -> {

            // check if admin already exists
            if (userRepository.findByEmail("admin@mail.com").isEmpty()) {

                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@mail.com");
                admin.setPassword("123456"); // ⚠️ later encrypt
                admin.setRole("ROLE_ADMIN");

                userRepository.save(admin);

                System.out.println("✅ Admin created");
            }
        };
    }
}