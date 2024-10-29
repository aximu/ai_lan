package com.example.languagelearning.payload;

import lombok.Data;

@Data
public class PracticeResultRequest {
    private String result;
    private String audioUrl;
}
