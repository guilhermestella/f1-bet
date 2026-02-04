package com.gs.f1bet;

import com.gs.f1bet.user.model.User;
import com.gs.f1bet.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@EnableFeignClients
@SpringBootApplication
public class F1BetApplication {
    public static void main(String[] args) {
        SpringApplication.run(F1BetApplication.class, args);
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner run(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            var admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .balance(BigDecimal.valueOf(100))
                    .build();

            var foo = User.builder()
                    .username("foo")
                    .password(passwordEncoder.encode("foo"))
                    .balance(BigDecimal.valueOf(100))
                    .build();

            var bar = User.builder()
                    .username("bar")
                    .password(passwordEncoder.encode("bar"))
                    .balance(BigDecimal.valueOf(100))
                    .build();

            userRepository.saveAll(List.of(admin, foo, bar));
        };
    }
}