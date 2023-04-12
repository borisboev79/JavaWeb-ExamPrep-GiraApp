package com.example.giraapp.repository;

import com.example.giraapp.model.entity.StyleEntity;
import com.example.giraapp.model.enums.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<StyleEntity, Long> {

    Optional<StyleEntity> findByName(Style style);
}
