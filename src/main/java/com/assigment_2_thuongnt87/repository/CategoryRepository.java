package com.assigment_2_thuongnt87.repository;


import java.util.Optional;
import java.util.UUID;

import com.assigment_2_thuongnt87.entities.catalog.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
  Optional<Category> findBySlug(String slug);
}
