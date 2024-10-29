package com.example.languagelearning.controller;

import com.example.languagelearning.model.User;
import com.example.languagelearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfile(@AuthenticationPrincipal UserDetails userDetails, @RequestBody User updatedUser) {
        User user = userService.findByEmail(userDetails.getUsername());
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        User savedUser = userService.updateUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
