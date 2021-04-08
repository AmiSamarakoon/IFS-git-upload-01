package com.example.demo.service;


import com.example.demo.model.TrainingSession;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.repository.TrainingSessionRepository;
import com.example.demo.repository.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TrainingSessionServiceImpl implements TrainingSessionService {

	
	@Autowired
	private TrainingSessionRepository trainingSessionRepository;

	@Autowired
	private TrainerRepository trainerRepository;
	
	@Autowired
	private VirtualMachineRepository virtualMachineRepository;
	
	@Override
	@Transactional
	public List<TrainingSession> getTrainingSessions() {


		return trainingSessionRepository.findAll();
	}


	@Override
	@Transactional
	public void saveTrainingSession(TrainingSession trainingSession) {
		System.out.println("saving a training Session for the date " + trainingSession.getStartDate());



		//add trainers to the training Session
		

		for(int i=0 ; i<trainingSession.getTrainerids().length ; i++) {

			System.out.println("inside TrainingSession Service impl addTrainer  loop");

			trainingSession.add(trainerRepository.getOne(trainingSession.getTrainerids()[i]));

		}


		//add VMs to the Training Session
		
	for(int i=0 ; i<trainingSession.getVmIds().length ; i++) {


		long num = Long.parseLong(trainingSession.getVmIds()[i]);

		System.out.println("inside TrainingSession Service impl addVm loop");
		trainingSession.addVM(virtualMachineRepository.getOne(num));


			
		}		

		trainingSessionRepository.save(trainingSession);

	}


	@Override
	@Transactional
	public TrainingSession getTrainingSession(String theId) {

//		trainingSessionRepository.getOne(theId);
//		TrainingSession trainingSession = trainingSessionDAO.getTrainingSession(theId);
//
//		return trainingSession;
		return null;
	}


	@Override
	@Transactional
	public void updateTrainingSession(TrainingSession trainingSession, Long id) {


		TrainingSession trsession  = trainingSessionRepository.getOne(id);




		for(int i=0 ; i<trainingSession.getVmIds().length ; i++) {


			long num = Long.parseLong(trainingSession.getVmIds()[i]);

			System.out.println("inside TrainingSession Service impl addVm loop");
			trsession.addVM(virtualMachineRepository.findVirtualMachineByVirtualMachineId(num));

		}




		for(int i=0 ; i<trainingSession.getTrainerids().length ; i++) {

			System.out.println("inside TrainingSession Service impl addTrainer  loop");


			trsession.add(trainerRepository.findByTrainerId(trainingSession.getTrainerids()[i]));

		}



		trainingSessionRepository.save(trsession);



	}

}



	

