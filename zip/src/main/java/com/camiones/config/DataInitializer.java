package com.camiones.config;

import com.camiones.model.Usuario;
import com.camiones.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            UsuarioRepository repository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (repository.findByUsername("admin").isEmpty()) {

                Usuario admin = new Usuario(
                        "admin",
                        passwordEncoder.encode("admin123"),
                        "ADMIN"
                );

                repository.save(admin);
            }

            if (repository.findByUsername("supervisor").isEmpty()) {

                Usuario supervisor = new Usuario(
                        "supervisor",
                        passwordEncoder.encode("super123"),
                        "SUPERVISOR"
                );

                repository.save(supervisor);
            }
        };
    }
}