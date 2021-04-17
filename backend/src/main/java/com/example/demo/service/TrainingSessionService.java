package com.example.demo.service;


import com.example.demo.model.TrainingSession;

import java.util.List;

public interface TrainingSessionService {

	public List<TrainingSession> getTrainingSessions();

	public void saveTrainingSession(TrainingSession trainingSession );

	public TrainingSession getTrainingSession(String theId);

	public void updateTrainingSession(TrainingSession trainingSession, Long id);


}
