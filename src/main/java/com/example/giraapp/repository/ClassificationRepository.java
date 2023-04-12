package com.example.giraapp.repository;

import com.example.giraapp.model.entity.Classification;
import com.example.giraapp.model.enums.ClassificationName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {

    Optional<Classification> findByClassificationName(ClassificationName classificationName);
}
