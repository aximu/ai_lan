package com.example.languagelearning.repository;

import com.example.languagelearning.model.PracticeSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PracticeSessionRepository extends MongoRepository<PracticeSession, String> {
}
