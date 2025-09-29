package com.example.titles.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<TitleEntity,Long> {
}
