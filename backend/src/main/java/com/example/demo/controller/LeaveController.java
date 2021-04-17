package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.example.demo.model.Trainer;
import com.example.demo.model.User;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.AllArgsConstructor;

//import com.example.datatransferobject.CountType;
import com.example.demo.model.Leave;
//import com.example.repositories.LeaveRepository;
import com.example.demo.service.LeaveService;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@AllArgsConstructor

public class LeaveController {

    //private LeaveRepository leaveRepository;

    private LeaveService leaveService;

    private TrainerRepository trainerRepository;

    //	@GetMapping("/person/vData/percentcounttype")
//	public List<CountType> getPercentageGroupByType(){
//		return leaveService.getPercentageGroupByType();
//	}
//
    public Trainer getTrainer() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;


        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        System.out.println("----------user name is ------------------------- in getTrainerFUnction LeaveController" + username);
        return trainerRepository.findByName(username);
    }


    @GetMapping("/person")
    public List<Leave> getLeave() {
//getLeavesByTrainer
        Trainer trainer = getTrainer();



        List<Leave> leaveList = leaveService.getLeaveByUserId(trainer.getTrainerId());
        //System.out.println(leaveList.get(1));

        return leaveList;
    }
//    @Transactional(readOnly = true)
//    public List<Leave> getLeaves(){
//        return leaveRepository.getAllLeaveDueDateDesc();
//    }

    //add
    @PostMapping("/person")
    public Leave addLeave(@RequestBody Leave leave) {
        System.out.println("----------Trainer Id is ------------------------- in addLeave LeaveController" );

        Trainer trainer= getTrainer();


        System.out.println("----------Trainer Id is ------------------------- in addLeave LeaveController" + trainer.getTrainerId());


        leave.setTrainer(trainer);

        return leaveService.save(leave);
    }

    @GetMapping("/person/{id}")
    public Leave addLeave(@PathVariable int id) {
        return leaveService.getLeaveById(id).orElseThrow(() -> new EntityNotFoundException("Requested leave not found"));
    }
    //update
    //Leave
    @PutMapping("/person/{id}")
    public ResponseEntity<?> addLeave(@RequestBody Leave leavePara, @PathVariable int id) {
        if (leaveService.existById(id)) {
            Leave leave = leaveService.getLeaveById(id).orElseThrow(() -> new EntityNotFoundException("Requested leave not found"));
            leave.setTitle(leavePara.getTitle());
            leave.setDate(leavePara.getDate());
            leave.setType(leavePara.getType());
            leave.setDescription(leavePara.getDescription());

            leaveService.save(leave);
            return ResponseEntity.ok().body(leave);

        } else {

            HashMap<String, String> message = new HashMap<>();

            message.put("message", id + "leave not found or matched");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> deleteLeave(@PathVariable int id) {
        if (leaveService.existById(id)) {

            leaveService.delete(id);

            HashMap<String, String> message = new HashMap<>();

            message.put("message", id + " leave removed");

            return ResponseEntity.status(HttpStatus.OK).body(message);

        } else {

            HashMap<String, String> message = new HashMap<>();

            message.put("message", id + "leave not found or matched");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
    //manager
    @GetMapping("/leave/all")
    public List<Leave> getLeaves(){
        List<Leave> leaves = leaveService.getLeaves();
        System.out.println(leaves);
        return leaves;
    }

    //	@GetMapping("/all")
//	public List<Leave> getLeave(){
//		return leaveRepository.findAll();
//	}
}
