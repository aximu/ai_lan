package com.example.languagelearning.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "practice_sessions")
public class PracticeSession {
    @Id
    private String id;
    private String type;
    private String userId;
    private Object content;
    private LocalDateTime createdAt;
}
