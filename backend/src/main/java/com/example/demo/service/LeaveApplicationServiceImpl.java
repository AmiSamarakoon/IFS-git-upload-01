//package com.example.demo.service;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import com.example.demo.model.LeaveApplication;
//import com.example.demo.model.Trainer;
//import com.example.demo.repository.LeaveApplicationRepository;
//import com.example.demo.repository.TrainerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class LeaveApplicationServiceImpl implements LeaveApplicationService {
//
//	@Autowired
//	private LeaveApplicationRepository  leaveApplicationRepository;
//
//	@Autowired
//	private TrainerRepository  trainerRepository;
//





//NOT MINE










//
//
//	@Override
//	@Transactional
//	public List<LeaveApplication> listAllLeaves() {
//
//		return leaveApplicationRepository.findAll();
//	}
//
//	@Override
//	@Transactional
//	public void saveOrUpdate(LeaveApplication leaveapplication) {
//
//
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username;
//		if (principal instanceof UserDetails) {
//			 username = ((UserDetails)principal).getUsername();
//		} else {
//			 username = principal.toString();
//		}
//
//		Trainer trainer = (Trainer) trainerRepository.findByName(username);
//
//		leaveapplication.setTrainer(trainer);
//
//
//		leaveApplicationRepository.save(leaveapplication);
//
//
//	}
//
//	@Override
//	@Transactional
//	public LeaveApplication findLeaveApplicationById(long id) {
//
//		return leaveApplicationRepository.getOne(id);
//
//	}
//
//	@Override
//	@Transactional
//	public void deleteLeaveApplication(long id) {
//
//		leaveApplicationRepository.deleteById(id);
//
//	}
//
//}
