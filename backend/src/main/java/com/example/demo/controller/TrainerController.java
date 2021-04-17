package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Trainer;
import com.example.demo.repository.TrainerRepository;
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

    //get all trainers
    @GetMapping("/trainers")
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    //get trainer by id
    @GetMapping("/trainers/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id) {
        Trainer trainer = trainerRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Trainer Not Found"));
        return ResponseEntity.ok(trainer);
    }

    @GetMapping("/trainers/{type}/{datestring}")
    public List<Trainer> getTrainerByType(@PathVariable String type , @PathVariable String datestring) throws ParseException {

        System.out.println("Data String is data string" + datestring);

        List<Trainer> trainers = trainerRepository.findByType(type);

        List<Trainer> availableTrainers =  new ArrayList<Trainer>();

        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");

        Date date=formatter2.parse(datestring);

        System.out.println("date is " + date);





        for(int i=0 ; i<trainers.size() ; i++){

            int availability = 1; // if one day is busy it will show 0


            for( int j =0 ; j< trainers.get(i).getTrainingSessions().size() ; j++){

                Date date1 = formatter2.parse(trainers.get(i).getTrainingSessions().get(j).getStartDate().toString());
                System.out.println(" Relevant training Session date is  " + date1);

                for(int k=0 ; k<trainers.get(i).getTrainingSessions().get(j).getDuration() ; k++){

                    //increment date


                    String dt = date1.toString();  // Start date
                    Calendar c = Calendar.getInstance();
                    c.setTime(date1);
                    c.add(Calendar.DATE, k);  // number of days to add

                    System.out.println("Duration Function date is " + c.getTime().toString() + " After adding " + k);


                    if(c.getTime().toString().equals(date.toString())){

                        System.out.println("---------------busy because of -------------" + c.getTime().toString() + " si equal to " +date.toString() );
                        availability = 0;
                    }

                }



            }

            if(availability == 1 ){

                availableTrainers.add(trainers.get(i));
            }

        }


        return availableTrainers;
    }



}
