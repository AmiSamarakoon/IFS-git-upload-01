package com.example.demo.controller;


import com.example.demo.dao.TrainingSessionDao;
import com.example.demo.model.TrainingSession;
import com.example.demo.payload.SortRequestTrainingSessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sort")
public class SortRequestTrainingSessionsController {

    @Autowired
    TrainingSessionDao trainingSessionDao;

    @PostMapping("/trainingSessions")
    public List<TrainingSession> userAccess(@RequestBody SortRequestTrainingSessions request) {
        List<TrainingSession> data = trainingSessionDao.findData(request);
        return data;
    }
}
