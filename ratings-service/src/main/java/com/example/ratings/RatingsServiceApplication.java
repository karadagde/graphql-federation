package com.example.ratings;

import com.example.ratings.persistance.RatingEntity;
import com.example.ratings.persistance.RatingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan(basePackages = "com.example.ratings.persistance")
public class RatingsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatingsServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner seed(RatingRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(RatingEntity.builder().userId(1L).titleId(1L).rate(4.5).build());
                repo.save(RatingEntity.builder().userId(2L).titleId(1L).rate(3.7).build());
            }
        };
    }
}