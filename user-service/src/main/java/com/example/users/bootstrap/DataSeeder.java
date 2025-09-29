package com.example.users.bootstrap;

import com.example.users.persistence.UserEntity;
import com.example.users.persistence.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(UserRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(UserEntity.builder().name("Ada Lovelace").email("ada@example.com").build());
                repo.save(UserEntity.builder().name("Grace Hopper").email("grace@example.com").build());
            }
        };
    }
}
