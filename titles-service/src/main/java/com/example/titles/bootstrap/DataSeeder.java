package com.example.titles.bootstrap;


import com.example.titles.persistence.TitleEntity;
import com.example.titles.persistence.TitleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(TitleRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(TitleEntity.builder().name("Batman Begins").year(2018).build());
                repo.save(TitleEntity.builder().name("Matrix").year(1999).build());
            }
        };
    }
}
