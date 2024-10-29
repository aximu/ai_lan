package com.example.languagelearning.controller;

import com.example.languagelearning.model.PracticeSession;
import com.example.languagelearning.model.User;
import com.example.languagelearning.payload.PracticeResultRequest;
import com.example.languagelearning.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/practice")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    @GetMapping("/{type}")
    public ResponseEntity<?> getPracticeSession(@PathVariable String type, @AuthenticationPrincipal UserDetails userDetails) {
        PracticeSession session = practiceService.createPracticeSession(type, userDetails.getUsername());
        return ResponseEntity.ok(session);
    }

    @PostMapping("/{type}")
    public ResponseEntity<?> submitPracticeResult(@PathVariable String type, @RequestBody PracticeResultRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        User updatedUser = practiceService.submitPracticeResult(userDetails.getUsername(), type, request.getResult(), request.getAudioUrl());
        return ResponseEntity.ok(updatedUser);
    }
}
