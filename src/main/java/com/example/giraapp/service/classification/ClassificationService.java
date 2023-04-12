package com.example.giraapp.service.classification;

import com.example.giraapp.init.DatabaseInitialization;
import com.example.giraapp.model.entity.Classification;
import com.example.giraapp.model.enums.ClassificationName;
import com.example.giraapp.repository.ClassificationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationService implements DatabaseInitialization {

    private final ClassificationRepository classificationRepository;

    @Autowired
    public ClassificationService(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @PostConstruct
    @Override
    public void dbInit() {
        if (!isDbInit()) {
            List<Classification> styles = (List.of(

                    Classification.builder().classificationName(ClassificationName.Bug).description("Nasty bugs").build(),
                    Classification.builder().classificationName(ClassificationName.Feature).description("Something that we want to have").build(),
                    Classification.builder().classificationName(ClassificationName.Support).description("When we need someone to look into stuff").build(),
                    Classification.builder().classificationName(ClassificationName.Other).description("Everything that we're not exactly sure what, goes here").build()

            )
            );
            this.classificationRepository.saveAllAndFlush(styles);

        }
    }

    @Override
    public boolean isDbInit() {
        return this.classificationRepository.count() > 0;
    }
}
