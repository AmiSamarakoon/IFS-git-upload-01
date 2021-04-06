package com.example.demo.controller;

import com.example.demo.dao.TrainingSessionDao;
import com.example.demo.model.TrainingSession;
import com.example.demo.payload.SortRequest;
import com.example.demo.repository.TrainingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SortRequestController {

    @Autowired
    TrainingSessionDao trainingSessionDao;

    @PostMapping("/api/test/trainer")

    public List<TrainingSession> userAccess(@RequestBody SortRequest request) {
        List<TrainingSession> data = trainingSessionDao.findData(request);
        return data;
    }
}
