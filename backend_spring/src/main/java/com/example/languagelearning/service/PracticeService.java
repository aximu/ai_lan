package com.example.languagelearning.service;

import com.example.languagelearning.model.PracticeSession;
import com.example.languagelearning.model.User;
import com.example.languagelearning.repository.PracticeSessionRepository;
import com.example.languagelearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Service
public class PracticeService {

    @Autowired
    private PracticeSessionRepository practiceSessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String AI_SERVICE_URL = "http://localhost:8000";

    public PracticeSession createPracticeSession(String type, String userId) {
        String content = restTemplate.getForObject(AI_SERVICE_URL + "/generate_practice/" + type, String.class);
        
        PracticeSession session = new PracticeSession();
        session.setType(type);
        session.setUserId(userId);
        session.setContent(content);
        
        return practiceSessionRepository.save(session);
    }

    public User submitPracticeResult(String userId, String type, String result, String audioUrl) {
        // Call AI service for evaluation
        EvaluationResult evaluationResult = restTemplate.postForObject(
            AI_SERVICE_URL + "/evaluate_practice",
            new EvaluationRequest(type, result, audioUrl),
            EvaluationResult.class
        );

        // Update user progress
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setLearningProgress(user.getLearningProgress() + evaluationResult.getProgressIncrement());
        return userRepository.save(user);
    }

    @Data
    private static class EvaluationRequest {
        private String type;
        private String result;
        private String audioUrl;

        public EvaluationRequest(String type, String result, String audioUrl) {
            this.type = type;
            this.result = result;
            this.audioUrl = audioUrl;
        }
    }

    @Data
    private static class EvaluationResult {
        private double progressIncrement;
        // Add other fields as needed
    }
}
