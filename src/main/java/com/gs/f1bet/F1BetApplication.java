package com.gs.f1bet;

import com.gs.f1bet.user.model.User;
import com.gs.f1bet.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableFeignClients
@SpringBootApplication
public class F1BetApplication {
    public static void main(String[] args) {
        SpringApplication.run(F1BetApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            var admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .build();

            userRepository.save(admin);
        };
    }
}