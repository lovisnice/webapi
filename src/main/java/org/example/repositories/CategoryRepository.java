package org.example.repositories;

import org.example.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>
{
    List<CategoryEntity> findByNameLikeIgnoreCase(String name);
    Page<CategoryEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}