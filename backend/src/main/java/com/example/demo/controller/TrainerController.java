package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Trainer;
import com.example.demo.model.User;
import com.example.demo.model.VirtualMachine;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TrainerController {

    @Autowired
    private TrainerRepository trainerRepository;

    //get all trainer
    @GetMapping("/trainers")
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @GetMapping("/trainers/{type}/{datestring}")
    public List<Trainer> getTrainerByType(@PathVariable String type , @PathVariable String datestring) throws ParseException {

        System.out.println("Data String is data string" + datestring);

        List<Trainer> trainers = trainerRepository.findByType(type);

        List<Trainer> availableTrainers =  new ArrayList<Trainer>();

        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-dd-MM");

        Date date=formatter2.parse(datestring);

        System.out.println("date is " + date);

        for(int i=0 ; i<trainers.size() ; i++){

            int availability = 1; // if one day is busy it will show 0


            for( int j =0 ; j< trainers.get(i).getTrainingSessions().size() ; j++){

                Date date1 = formatter2.parse(trainers.get(i).getTrainingSessions().get(j).getStartDate().toString());
                System.out.println(date1 + "adding one day" + date1);




                if(date1.toString().equals(date.toString())){

                    System.out.println("busy");
                    availability = 0;
                }

            }

            if(availability == 1 ){

                availableTrainers.add(trainers.get(i));
            }

        }


        return availableTrainers;
    }
}
