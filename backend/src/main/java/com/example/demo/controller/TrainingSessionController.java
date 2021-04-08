package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Trainer;
import com.example.demo.model.TrainingSession;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.repository.TrainingSessionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VirtualMachineRepository;
import com.example.demo.service.TrainingSessionService;
import com.example.demo.service.TrainingSessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class TrainingSessionController {


    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TrainingSessionService trainingSessionService;

    @Autowired
    private VirtualMachineRepository virtualMachineRepository;



    //get all training session api
    @GetMapping("/trainingSessions")
    public List<TrainingSession> getAllTrainingSession() {
        System.out.println("Getting training Sessions ------*******------12-----*---*-*-*------------------------------****************------------***");

        return trainingSessionRepository.findAll();
    }


    @GetMapping("/trainingSessionByTrainer/{username}")
    public List<TrainingSession> getAllTrainingSessionbyTrainer(@PathVariable String username) {



      String trainerName = userRepository.findUserByUsername(username).getName();

     Trainer trainer = trainerRepository.findByName(trainerName);
      List<TrainingSession> trainingSessions  = trainer.getTrainingSessions();


        return trainingSessions;
    }




    //add training session
    @PostMapping("/trainingSessions")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void createTrainingSession(@RequestBody TrainingSession trainingSession) {
         trainingSessionService.saveTrainingSession(trainingSession);
    }




    //get training session by id
    @GetMapping("/trainingSessions/{id}")

    public ResponseEntity<TrainingSession> getTrainingSessionById(@PathVariable Long id) {
        TrainingSession trainingSession = trainingSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training Session Not Found"));
        return ResponseEntity.ok(trainingSession);
    }


    //update training session
    @PutMapping("/trainingSessions/{id}")

    public ResponseEntity<TrainingSession> updateTrainingSession(@PathVariable Long id, @RequestBody TrainingSession trainingSessionDetails) {

        trainingSessionService.updateTrainingSession(trainingSessionDetails , id);


        return ResponseEntity.ok(trainingSessionDetails);

    }

    //delete training session
    @DeleteMapping("/trainingSessions/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<Map<String, Boolean>> deleteTrainingSession(@PathVariable Long id) {
        TrainingSession trainingSession = trainingSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Training Session Not Found")
                );

        trainingSessionRepository.delete(trainingSession);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
