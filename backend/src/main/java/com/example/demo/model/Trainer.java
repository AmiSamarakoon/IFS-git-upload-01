package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainer")
public class Trainer {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainerId;

    @Column(name = "name")
    private  String name;

    @Column(name = "Type")
    private  String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Trainer() {
    }

    @ManyToMany(fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "training_session_trainer",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "training_session_id")
    )

    @JsonIgnoreProperties("trainers")
    private List<TrainingSession> trainingSessions;


    public Trainer(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(List<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }



    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }


    public void add(TrainingSession tempSession) {

        if(trainingSessions ==null) {

            trainingSessions = new ArrayList<TrainingSession>();

        }

        trainingSessions.add(tempSession);


    }



    @OneToMany(fetch = FetchType.LAZY,mappedBy = "trainer" ,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JsonIgnoreProperties("trainer")
    private List<LeaveApplication> leaveApplications;


    public List<LeaveApplication> getLeaveApplications() {
        return leaveApplications;
    }

    public void setLeaveApplications(List<LeaveApplication> leaveApplications) {
        this.leaveApplications = leaveApplications;
    }






}
