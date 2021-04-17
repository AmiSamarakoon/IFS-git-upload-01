package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import example.sam.datatransferobject.CountType;
import com.example.demo.model.Leave;
import com.example.demo.repository.LeaveRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Transactional(readOnly = true)
    public List<Leave> getLeaves(){
        return leaveRepository.getAllLeaveDueDateDesc();
    }



//    @Override
//    @Transactional
//    public void saveOrUpdate(LeaveApplication leaveapplication) {
//
//
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
//
//        Trainer trainer = (Trainer) trainerRepository.findByName(username);
//
//        leaveapplication.setTrainer(trainer);
//
//
//        leaveApplicationRepository.save(leaveapplication);
//
//
//    }


    @Transactional
    public Leave save(Leave leave) {
        return leaveRepository.saveAndFlush(leave);
    }

    @Transactional(readOnly = true)
    public boolean existById(int id) {
        return leaveRepository.existsById(id);

    }

    @Transactional(readOnly = true)
    public Optional<Leave> getLeaveById(int id) {
        // TODO Auto-generated method stub
        return leaveRepository.findById(id);
    }

    public void delete(int id) {
        // TODO Auto-generated method stub
        leaveRepository.deleteById(id);

    }



    //according to the trainer
    public List<Leave> getLeaveByUserId(Long  id){
        return leaveRepository.getAllLeaveDueDateDescnd(id);
    }

    //manager
    public List<Leave> getAllLeaves(){

        return leaveRepository.findAll();

    };

//	public List<CountType> getPercentageGroupByType() {
//		return leaveRepository.getPercentageGroupByType();
//	}
}
