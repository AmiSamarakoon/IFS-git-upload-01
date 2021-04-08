package com.example.demo.controller;

import com.example.demo.dao.TrainingSessionDao;
import com.example.demo.model.TrainingSession;
import com.example.demo.payload.SortRequest;
import com.example.demo.repository.TrainingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/sort")
public class SortRequestController {

    @Autowired
    TrainingSessionDao trainingSessionDao;

    @PostMapping("/trainingSessions")

    public List<TrainingSession> userAccess(@RequestBody SortRequest request) {
        List<TrainingSession> data = trainingSessionDao.findData(request);
        return data;
    }
}
