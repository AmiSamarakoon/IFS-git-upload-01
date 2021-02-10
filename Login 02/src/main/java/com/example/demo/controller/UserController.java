package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //get all trainers
    @GetMapping("/trainers")
    public List<User> getAllTrainers() {
        return userRepository.findAll();
    }

    //get trainer by id
    @GetMapping("/trainers/{id}")
    public ResponseEntity<User> getTrainerById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer Not Found"));
        return ResponseEntity.ok(user);
    }
}
