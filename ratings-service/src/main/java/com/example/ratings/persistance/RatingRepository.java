package com.example.ratings.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
    List<RatingEntity> findByUserId(Long userId);
    List<RatingEntity> findByTitleId(Long titleId);
}
